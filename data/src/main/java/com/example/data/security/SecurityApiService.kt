package com.example.data.security

import retrofit2.Response
import retrofit2.http.GET

interface SecurityApiService {
	@GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
	suspend fun vpStoreAppControl(): Response<String>
}
