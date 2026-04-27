package com.example.domain.auth.usecase

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.fake.FakeAuthRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LogoutUseCaseTest {
	private fun createUseCase(authRepository: AuthRepository = FakeAuthRepository()) = LogoutUseCase(authRepository)

	@Test
	fun `GIVEN repository returns success WHEN invoke THEN returns success`() =
		runTest {
			// Given
			val fakeRepo = FakeAuthRepository()
			fakeRepo.setLogoutSuccess()
			val useCase = createUseCase(authRepository = fakeRepo)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Success)
		}

	@Test
	fun `GIVEN repository returns error WHEN invoke THEN returns the error`() =
		runTest {
			// Given
			val fakeRepo = FakeAuthRepository()
			fakeRepo.setLogoutError(DataError.Local.Unknown)
			val useCase = createUseCase(authRepository = fakeRepo)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DataError.Local.Unknown, (result as Result.Failure).error)
		}
}
