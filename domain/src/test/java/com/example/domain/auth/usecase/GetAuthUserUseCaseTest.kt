package com.example.domain.auth.usecase

import app.cash.turbine.test
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.builder.user
import com.example.domain.fake.FakeAuthRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAuthUserUseCaseTest {
	private fun createUseCase(authRepository: AuthRepository = FakeAuthRepository()) = GetAuthUserUseCase(authRepository)

	@Test
	fun `GIVEN a logged in user WHEN invoke THEN emits the user`() =
		runTest {
			// Given
			val expectedUser = user { withUsername("testuser") }
			val useCase = createUseCase(authRepository = FakeAuthRepository(initialUser = expectedUser))

			// When / Then
			useCase().test {
				assertEquals(expectedUser, awaitItem())
				cancelAndIgnoreRemainingEvents()
			}
		}

	@Test
	fun `GIVEN no user WHEN invoke THEN emits null`() =
		runTest {
			// Given
			val useCase = createUseCase(authRepository = FakeAuthRepository(initialUser = null))

			// When / Then
			useCase().test {
				assertNull(awaitItem())
				cancelAndIgnoreRemainingEvents()
			}
		}

	@Test
	fun `GIVEN a logged in user WHEN repository clears THEN emits null`() =
		runTest {
			// Given
			val fakeRepo = FakeAuthRepository(initialUser = user { withUsername("testuser") })
			val useCase = createUseCase(authRepository = fakeRepo)

			// When / Then
			useCase().test {
				awaitItem()
				fakeRepo.clear()
				assertNull(awaitItem())
				cancelAndIgnoreRemainingEvents()
			}
		}
}
