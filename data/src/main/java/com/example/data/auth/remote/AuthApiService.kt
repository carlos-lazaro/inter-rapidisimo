package com.example.data.auth.remote

import com.example.data.auth.remote.dto.AuthRequestDto
import com.example.data.auth.remote.dto.AuthResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {
	@Headers(
		"Accept: text/json",
	)
	@POST("FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
	suspend fun authenticaUsuarioApp(
		@Header("Usuario") usuario: String,
		@Header("Identificacion") identificacion: String,
		@Header("IdUsuario") idUsuario: String,
		@Header("IdCentroServicio") idCentroServicio: String,
		@Header("NombreCentroServicio") nombreCentroServicio: String,
		@Header("IdAplicativoOrigen") idAplicativoOrigen: String,
		@Body authRequestDto: AuthRequestDto,
	): Response<AuthResponseDto>
}
