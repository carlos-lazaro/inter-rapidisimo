package com.example.data.tabla.remote

import com.example.data.tabla.remote.dto.TablaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TablaApiService {
	@GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/{activo}")
	suspend fun obtenerEsquema(
		@Header("usuario") usuario: String,
		@Path("activo") activo: Boolean = true,
	): Response<List<TablaDto>>
}
