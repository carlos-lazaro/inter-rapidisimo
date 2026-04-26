package com.example.domain.table.usecase

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.domain.fake.FakeTableRepository
import com.example.domain.table.repository.TableRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SyncTablesUseCaseTest {
	private fun createUseCase(
		repository: TableRepository = FakeTableRepository(),
	) = SyncTablesUseCase(repository)

	@Test
	fun `GIVEN repository returns success WHEN invoke THEN returns success`() =
		runTest {
			// Given
			val fakeRepo = FakeTableRepository()
			fakeRepo.setSyncSuccess()
			val useCase = createUseCase(repository = fakeRepo)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Success)
		}

	@Test
	fun `GIVEN repository returns remote error WHEN invoke THEN returns the error`() =
		runTest {
			// Given
			val fakeRepo = FakeTableRepository()
			fakeRepo.setSyncError(DataError.Remote.NoInternet)
			val useCase = createUseCase(repository = fakeRepo)

			// When
			val result = useCase()

			// Then
			assertTrue(result is Result.Failure)
			assertEquals(DataError.Remote.NoInternet, (result as Result.Failure).error)
		}
}
