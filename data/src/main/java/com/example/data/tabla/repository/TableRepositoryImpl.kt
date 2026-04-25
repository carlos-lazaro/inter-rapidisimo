package com.example.data.tabla.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.asEmptyResult
import com.example.core.util.onSuccess
import com.example.data.tabla.local.TablaLocalDataSource
import com.example.data.tabla.mapper.toDomain
import com.example.data.tabla.mapper.toEntity
import com.example.data.tabla.remote.TablaRemoteDataSource
import com.example.domain.table.repository.TableRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TableRepositoryImpl
	@Inject
	constructor(
		private val remoteDataSource: TablaRemoteDataSource,
		private val localDataSource: TablaLocalDataSource,
	) : TableRepository {
		override fun getTables() = localDataSource.getAllTablas().map { it.toDomain() }

		override suspend fun syncTables(usuario: String): EmptyResult<DataError> =
			remoteDataSource
				.obtenerEsquema(usuario = usuario)
				.onSuccess { dtos ->
					val tablas = dtos.mapNotNull { it.toEntity() }
					localDataSource.replaceTablas(tablas)
				}.asEmptyResult()
	}
