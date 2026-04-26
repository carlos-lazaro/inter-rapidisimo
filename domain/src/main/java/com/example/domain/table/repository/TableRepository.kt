package com.example.domain.table.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.domain.table.model.Table
import kotlinx.coroutines.flow.Flow

interface TableRepository {
	fun getTables(): Flow<List<Table>>

	suspend fun syncTables(usuario: String): EmptyResult<DataError>

	suspend fun clearTables(): EmptyResult<DataError.Local>
}
