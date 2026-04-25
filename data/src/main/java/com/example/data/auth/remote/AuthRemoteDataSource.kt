package com.example.data.auth.remote

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.data.auth.remote.dto.AuthRequestDto
import com.example.data.auth.remote.dto.AuthResponseDto
import com.example.data.util.safeApiCall
import com.example.domain.auth.model.AuthHeaders
import javax.inject.Inject

class AuthRemoteDataSource
	@Inject
	constructor(
		private val api: AuthApiService,
	) {
		suspend fun login(
			request: AuthRequestDto,
			headers: AuthHeaders,
		): Result<AuthResponseDto, DataError.Remote> =
			safeApiCall {
				api.authenticaUsuarioApp(
					usuario = headers.usuario,
					identificacion = headers.identificacion,
					idUsuario = headers.idUsuario,
					idCentroServicio = headers.idCentroServicio,
					nombreCentroServicio = headers.nombreCentroServicio,
					idAplicativoOrigen = headers.idAplicativoOrigen,
					authRequestDto = request,
				)
			}
	}
