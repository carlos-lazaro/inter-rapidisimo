package com.example.data.auth.remote.dto

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
	@SerialName("Usuario") val usuario: String,
	@SerialName("Password") val password: String,
	@SerialName("NomAplicacion") val nomAplicacion: String,
	@EncodeDefault @SerialName("Mac") val mac: String = "",
	@EncodeDefault @SerialName("Path") val path: String = "",
)
