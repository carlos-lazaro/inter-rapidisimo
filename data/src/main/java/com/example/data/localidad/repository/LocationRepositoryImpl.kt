package com.example.data.localidad.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.asEmptyResult
import com.example.core.util.onSuccess
import com.example.data.localidad.local.LocalidadLocalDataSource
import com.example.data.localidad.mapper.toDomain
import com.example.data.localidad.mapper.toEntity
import com.example.data.localidad.remote.LocalidadRemoteDataSource
import com.example.domain.location.repository.LocationRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl
	@Inject
	constructor(
		private val remoteDataSource: LocalidadRemoteDataSource,
		private val localDataSource: LocalidadLocalDataSource,
	) : LocationRepository {
		override fun getLocations() = localDataSource.getAllLocalidades().map { it.toDomain() }

		override suspend fun syncLocations(): EmptyResult<DataError> =
			remoteDataSource
				.obtenerLocalidadesRecogidas()
				.onSuccess {
					localDataSource.replaceLocalidades(it.toEntity())
				}.asEmptyResult()
	}
