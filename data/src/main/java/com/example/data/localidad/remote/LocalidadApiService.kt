package com.example.data.localidad.remote

import com.example.data.localidad.remote.dto.LocalidadDto
import retrofit2.Response
import retrofit2.http.GET

interface LocalidadApiService {
	@GET("apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
	suspend fun obtenerLocalidadesRecogidas(): Response<List<LocalidadDto>>
}
