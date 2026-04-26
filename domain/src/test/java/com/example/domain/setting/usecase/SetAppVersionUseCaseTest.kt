package com.example.domain.setting.usecase

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.domain.error.DomainError
import com.example.domain.fake.FakeSettingsRepository
import com.example.domain.setting.repository.SettingsRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SetAppVersionUseCaseTest {
	private fun createUseCase(
		settingsRepository: SettingsRepository = FakeSettingsRepository(),
	) = SetAppVersionUseCase(settingsRepository)

	@Test
	fun `GIVEN blank input WHEN invoke THEN returns InvalidAppVersion`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase("")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.InvalidAppVersion, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN non-numeric input WHEN invoke THEN returns InvalidAppVersion`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase("abc")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.InvalidAppVersion, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN zero value WHEN invoke THEN returns InvalidAppVersion`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase("0")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DomainError.InvalidAppVersion, (result as Result.Failure).error)
		}

	@Test
	fun `GIVEN valid version WHEN repo succeeds THEN returns success`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When
			val result = useCase("3")

			// Then
			assertTrue(result is Result.Success)
		}

	@Test
	fun `GIVEN valid version WHEN repo fails THEN returns LocalError`() =
		runTest {
			// Given
			val fakeRepo = FakeSettingsRepository()
			fakeRepo.setSetAppVersionError(DataError.Local.Unknown)
			val useCase = createUseCase(settingsRepository = fakeRepo)

			// When
			val result = useCase("3")

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(
				DomainError.LocalError(DataError.Local.Unknown),
				(result as Result.Failure).error,
			)
		}
}
