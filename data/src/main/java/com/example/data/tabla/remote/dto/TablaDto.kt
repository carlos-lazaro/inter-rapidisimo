package com.example.data.tabla.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TablaDto(
	@SerialName("NombreTabla") val nombreTabla: String?,
	@SerialName("Pk") val pk: String?,
	@SerialName("QueryCreacion") val queryCreacion: String?,
	@SerialName("BatchSize") val batchSize: Int?,
	@SerialName("Filtro") val filtro: String?,
	@SerialName("Error") val error: String?,
	@SerialName("NumeroCampos") val numeroCampos: Int?,
	@SerialName("MetodoApp") val metodoApp: String?,
	@SerialName("FechaActualizacionSincro") val fechaActualizacionSincro: String?,
)
