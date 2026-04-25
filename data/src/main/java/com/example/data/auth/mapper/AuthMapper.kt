package com.example.data.auth.mapper

import com.example.data.auth.local.entity.AuthEntity
import com.example.data.auth.remote.dto.AuthRequestDto
import com.example.data.auth.remote.dto.AuthResponseDto
import com.example.domain.auth.model.User
import com.example.domain.auth.model.UserForm

fun UserForm.toRequest() =
	AuthRequestDto(
		usuario = usuario,
		password = password,
		nomAplicacion = nomAplicacion,
	)

fun AuthResponseDto.toEntity(): AuthEntity? {
	if (usuario.isNullOrBlank()) return null

	return AuthEntity(
		usuario = usuario,
		identificacion = identificacion,
		nombre = nombre,
		apellido1 = apellido1,
		apellido2 = apellido2,
		cargo = cargo,
		aplicaciones = aplicaciones,
		ubicaciones = ubicaciones,
		mensajeResultado = mensajeResultado,
		idLocalidad = idLocalidad,
		nombreLocalidad = nombreLocalidad,
		nomRol = nomRol,
		idRol = idRol,
		tokenJWT = tokenJWT,
		modulosApp = modulosApp,
	)
}

fun AuthEntity.toDomain(): User? {
	if (usuario.isBlank()) return null
	return User(username = usuario, identification = identificacion, name = nombre)
}
