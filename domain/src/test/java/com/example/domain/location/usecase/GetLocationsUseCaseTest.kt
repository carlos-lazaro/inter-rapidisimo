package com.example.domain.location.usecase

import app.cash.turbine.test
import com.example.domain.builder.location
import com.example.domain.fake.FakeLocationRepository
import com.example.domain.location.repository.LocationRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetLocationsUseCaseTest {
	private fun createUseCase(
		repository: LocationRepository = FakeLocationRepository(),
	) = GetLocationsUseCase(repository)

	@Test
	fun `GIVEN empty repository WHEN invoke THEN emits empty list`() =
		runTest {
			// Given
			val useCase = createUseCase()

			// When / Then
			useCase().test {
				assertTrue(awaitItem().isEmpty())
				cancelAndIgnoreRemainingEvents()
			}
		}

	@Test
	fun `GIVEN locations in repository WHEN invoke THEN emits the list`() =
		runTest {
			// Given
			val fakeRepo = FakeLocationRepository(initialLocations = listOf(location { }))
			val useCase = createUseCase(repository = fakeRepo)

			// When / Then
			useCase().test {
				assertEquals(1, awaitItem().size)
				cancelAndIgnoreRemainingEvents()
			}
		}

	@Test
	fun `GIVEN repository updates WHEN locations change THEN flow re-emits`() =
		runTest {
			// Given
			val fakeRepo = FakeLocationRepository()
			val useCase = createUseCase(repository = fakeRepo)

			// When / Then
			useCase().test {
				assertTrue(awaitItem().isEmpty())
				fakeRepo.emit(location { })
				assertEquals(1, awaitItem().size)
				cancelAndIgnoreRemainingEvents()
			}
		}
}
