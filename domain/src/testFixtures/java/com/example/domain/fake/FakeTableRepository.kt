package com.example.domain.fake

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.table.model.Table
import com.example.domain.table.repository.TableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeTableRepository(
	initialTables: List<Table> = emptyList(),
) : TableRepository {
	private val _tables = MutableStateFlow(initialTables)

	private var syncResult: EmptyResult<DataError> = Result.Success(Unit)
	private var clearResult: EmptyResult<DataError.Local> = Result.Success(Unit)

	fun emit(vararg tables: Table) = _tables.update { tables.toList() }

	fun emitList(tables: List<Table>) = _tables.update { tables }

	fun clear() = _tables.update { emptyList() }

	fun getTablesSync() = _tables.value

	fun setSyncResult(result: EmptyResult<DataError>) {
		syncResult = result
	}

	fun setSyncSuccess() {
		syncResult = Result.Success(Unit)
	}

	fun setSyncError(error: DataError) {
		syncResult = Result.Failure(error)
	}

	fun setClearResult(result: EmptyResult<DataError.Local>) {
		clearResult = result
	}

	fun setClearSuccess() {
		clearResult = Result.Success(Unit)
	}

	fun setClearError(error: DataError.Local) {
		clearResult = Result.Failure(error)
	}

	override fun getTables(): Flow<List<Table>> = _tables

	override suspend fun syncTables(usuario: String): EmptyResult<DataError> = syncResult

	override suspend fun clearTables(): EmptyResult<DataError.Local> = clearResult
}
