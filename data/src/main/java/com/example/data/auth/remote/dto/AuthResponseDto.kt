package com.example.data.auth.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
	@SerialName("Usuario") val usuario: String?,
	@SerialName("Identificacion") val identificacion: String?,
	@SerialName("Nombre") val nombre: String?,
	@SerialName("Apellido1") val apellido1: String?,
	@SerialName("Apellido2") val apellido2: String?,
	@SerialName("Cargo") val cargo: String?,
	@SerialName("Aplicaciones") val aplicaciones: String?,
	@SerialName("Ubicaciones") val ubicaciones: String?,
	@SerialName("MensajeResultado") val mensajeResultado: Int?,
	@SerialName("IdLocalidad") val idLocalidad: String?,
	@SerialName("NombreLocalidad") val nombreLocalidad: String?,
	@SerialName("NomRol") val nomRol: String?,
	@SerialName("IdRol") val idRol: String?,
	@SerialName("TokenJWT") val tokenJWT: String?,
	@SerialName("ModulosApp") val modulosApp: String?,
)
