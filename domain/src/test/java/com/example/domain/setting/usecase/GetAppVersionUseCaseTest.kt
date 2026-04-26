package com.example.domain.setting.usecase

import app.cash.turbine.test
import com.example.domain.fake.FakeSettingsRepository
import com.example.domain.setting.repository.SettingsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAppVersionUseCaseTest {
	private fun createUseCase(
		settingsRepository: SettingsRepository = FakeSettingsRepository(),
	) = GetAppVersionUseCase(settingsRepository)

	@Test
	fun `GIVEN valid version WHEN invoke THEN emits version`() =
		runTest {
			// Given
			val useCase = createUseCase(settingsRepository = FakeSettingsRepository(initialAppVersion = 5))

			// When / Then
			useCase().test {
				assertEquals(5, awaitItem())
				cancelAndIgnoreRemainingEvents()
			}
		}

	@Test
	fun `GIVEN version is zero WHEN invoke THEN emits 1`() =
		runTest {
			// Given
			val useCase = createUseCase(settingsRepository = FakeSettingsRepository(initialAppVersion = 0))

			// When / Then
			useCase().test {
				assertEquals(1, awaitItem())
				cancelAndIgnoreRemainingEvents()
			}
		}

	@Test
	fun `GIVEN version is negative WHEN invoke THEN emits 1`() =
		runTest {
			// Given
			val useCase = createUseCase(settingsRepository = FakeSettingsRepository(initialAppVersion = -3))

			// When / Then
			useCase().test {
				assertEquals(1, awaitItem())
				cancelAndIgnoreRemainingEvents()
			}
		}
}
