package com.example.domain.auth.usecase

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.builder.user
import com.example.domain.error.DomainError
import com.example.domain.fake.FakeAuthRepository
import com.example.domain.fake.FakeCredentialEncoder
import com.example.domain.security.encoder.CredentialEncoder
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoginUseCaseTest {
	private fun createUseCase(
		authRepository: AuthRepository = FakeAuthRepository(),
		credentialEncoder: CredentialEncoder = FakeCredentialEncoder(),
	) = LoginUseCase(authRepository, credentialEncoder)

	@Test
	fun `GIVEN blank username WHEN invoke THEN returns UsernameEmpty`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase(usuario = "", password = "secret")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.UsernameEmpty, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN whitespace only username WHEN invoke THEN returns UsernameEmpty`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase(usuario = "   ", password = "secret")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.UsernameEmpty, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN blank password WHEN invoke THEN returns PasswordEmpty`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase(usuario = "user", password = "")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.PasswordEmpty, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN whitespace only password WHEN invoke THEN returns PasswordEmpty`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase(usuario = "user", password = "   ")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.PasswordEmpty, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN valid credentials WHEN invoke THEN encoded values are passed to repository`() =
		runTest {
			// Given
			val fakeEncoder = FakeCredentialEncoder()
			fakeEncoder.setEncodeResult("encoded_value")
			val fakeRepo = FakeAuthRepository()
			val useCase = createUseCase(authRepository = fakeRepo, credentialEncoder = fakeEncoder)

			// When
			useCase(usuario = "user", password = "secret")

			// Then
			assertEquals("encoded_value", fakeRepo.capturedUserForm?.usuario)
			assertEquals("encoded_value", fakeRepo.capturedUserForm?.password)
		}

	@Test
	fun `GIVEN valid credentials WHEN repository returns success THEN returns the user`() =
		runTest {
			// Given
			val expectedUser = user { withUsername("testuser") }
			val fakeRepo = FakeAuthRepository()
			fakeRepo.setLoginSuccess(expectedUser)
			val useCase = createUseCase(authRepository = fakeRepo)

			// When
			val result = useCase(usuario = "user", password = "secret")

			// Then
			assertTrue(result is Result.Success)
			assertEquals(expectedUser, (result as Result.Success).data)
		}

	@Test
	fun `GIVEN valid credentials WHEN repository returns remote error THEN maps to RemoteError`() =
		runTest {
			// Given
			val fakeRepo = FakeAuthRepository()
			fakeRepo.setLoginError(DataError.Remote.Unauthorized)
			val useCase = createUseCase(authRepository = fakeRepo)

			// When
			val result = useCase(usuario = "user", password = "secret")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(
				DomainError.RemoteError(DataError.Remote.Unauthorized),
				(result as Result.Failure).error,
			)
		}

	@Test
	fun `GIVEN valid credentials WHEN repository returns local error THEN maps to LocalError`() =
		runTest {
			// Given
			val fakeRepo = FakeAuthRepository()
			fakeRepo.setLoginError(DataError.Local.Unknown)
			val useCase = createUseCase(authRepository = fakeRepo)

			// When
			val result = useCase(usuario = "user", password = "secret")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(
				DomainError.LocalError(DataError.Local.Unknown),
				(result as Result.Failure).error,
			)
		}
}
