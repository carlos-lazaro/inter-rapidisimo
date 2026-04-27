@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.domain.auth.usecase

import com.example.core.util.Result
import com.example.domain.location.repository.LocationRepository
import com.example.domain.table.repository.TableRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ClearLocalCacheUseCaseTest {
	private val locationRepository = mockk<LocationRepository>()
	private val tableRepository = mockk<TableRepository>()

	@Before
	fun setup() {
		coEvery { locationRepository.clearLocations() } returns Result.Success(Unit)
		coEvery { tableRepository.clearTables() } returns Result.Success(Unit)
	}

	private fun createUseCase(scope: CoroutineScope) = ClearLocalCacheUseCase(locationRepository, tableRepository, scope)

	@Test
	fun `GIVEN both repos WHEN invoke THEN clearLocations is called`() =
		runTest {
			// Given
			val useCase = createUseCase(scope = this)

			// When
			useCase()
			advanceUntilIdle()

			// Then
			coVerify { locationRepository.clearLocations() }
		}

	@Test
	fun `GIVEN both repos WHEN invoke THEN clearTables is called`() =
		runTest {
			// Given
			val useCase = createUseCase(scope = this)

			// When
			useCase()
			advanceUntilIdle()

			// Then
			coVerify { tableRepository.clearTables() }
		}
}
