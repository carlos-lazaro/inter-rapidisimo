package com.example.data.tabla.remote

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.data.tabla.remote.dto.TablaDto
import com.example.data.util.safeApiCall
import javax.inject.Inject

class TablaRemoteDataSource
	@Inject
	constructor(
		private val api: TablaApiService,
	) {
		suspend fun obtenerEsquema(usuario: String): Result<List<TablaDto>, DataError.Remote> =
			safeApiCall {
				api.obtenerEsquema(usuario = usuario)
			}
	}
