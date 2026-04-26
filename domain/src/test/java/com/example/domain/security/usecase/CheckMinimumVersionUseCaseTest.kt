package com.example.domain.security.usecase

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.domain.error.DomainError
import com.example.domain.builder.securityConfig
import com.example.domain.fake.FakeSecurityRepository
import com.example.domain.fake.FakeSettingsRepository
import com.example.domain.security.repository.SecurityRepository
import com.example.domain.setting.repository.SettingsRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CheckMinimumVersionUseCaseTest {
	private fun createUseCase(
		securityRepository: SecurityRepository = FakeSecurityRepository(),
		settingsRepository: SettingsRepository = FakeSettingsRepository(),
	) = CheckMinimumVersionUseCase(securityRepository, settingsRepository)

	@Test
	fun `GIVEN current version meets minimum WHEN invoke THEN returns success`() =
		runTest {
			// Given
			val useCase = createUseCase(
				securityRepository = FakeSecurityRepository(initialConfig = securityConfig { withMinimumVersion(5) }),
				settingsRepository = FakeSettingsRepository(initialAppVersion = 5),
			)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Success)
		}

	@Test
	fun `GIVEN current version above minimum WHEN invoke THEN returns success`() =
		runTest {
			// Given
			val useCase = createUseCase(
				securityRepository = FakeSecurityRepository(initialConfig = securityConfig { withMinimumVersion(5) }),
				settingsRepository = FakeSettingsRepository(initialAppVersion = 6),
			)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Success)
		}

	@Test
	fun `GIVEN current version below minimum WHEN invoke THEN returns AppVersionOutdated`() =
		runTest {
			// Given
			val useCase = createUseCase(
				securityRepository = FakeSecurityRepository(initialConfig = securityConfig { withMinimumVersion(5) }),
				settingsRepository = FakeSettingsRepository(initialAppVersion = 4),
			)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.AppVersionOutdated, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN security repo fails WHEN invoke THEN returns RemoteError`() =
		runTest {
			// Given
			val fakeSecurityRepo = FakeSecurityRepository()
			fakeSecurityRepo.setGetConfigError(DataError.Remote.ServerError)
			val useCase = createUseCase(securityRepository = fakeSecurityRepo)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(
				DomainError.RemoteError(DataError.Remote.ServerError),
				(result as Result.Failure).error,
			)
		}
}
