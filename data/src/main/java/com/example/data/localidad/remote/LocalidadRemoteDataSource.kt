package com.example.data.localidad.remote

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.data.localidad.remote.dto.LocalidadDto
import com.example.data.util.safeApiCall
import javax.inject.Inject

class LocalidadRemoteDataSource
	@Inject
	constructor(
		private val api: LocalidadApiService,
	) {
		suspend fun obtenerLocalidadesRecogidas(): Result<List<LocalidadDto>, DataError.Remote> =
			safeApiCall {
				api.obtenerLocalidadesRecogidas()
			}
	}
