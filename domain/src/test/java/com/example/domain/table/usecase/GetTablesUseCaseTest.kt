package com.example.domain.table.usecase

import app.cash.turbine.test
import com.example.domain.builder.table
import com.example.domain.fake.FakeTableRepository
import com.example.domain.table.repository.TableRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTablesUseCaseTest {
	private fun createUseCase(repository: TableRepository = FakeTableRepository()) = GetTablesUseCase(repository)

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
	fun `GIVEN tables in repository WHEN invoke THEN emits the list`() =
		runTest {
			// Given
			val fakeRepo = FakeTableRepository(initialTables = listOf(table { }))
			val useCase = createUseCase(repository = fakeRepo)

			// When / Then
			useCase().test {
				assertEquals(1, awaitItem().size)
				cancelAndIgnoreRemainingEvents()
			}
		}

	@Test
	fun `GIVEN repository updates WHEN tables change THEN flow re-emits`() =
		runTest {
			// Given
			val fakeRepo = FakeTableRepository()
			val useCase = createUseCase(repository = fakeRepo)

			// When / Then
			useCase().test {
				assertTrue(awaitItem().isEmpty())
				fakeRepo.emit(table { })
				assertEquals(1, awaitItem().size)
				cancelAndIgnoreRemainingEvents()
			}
		}
}
