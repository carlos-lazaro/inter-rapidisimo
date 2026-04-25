package com.example.data.localidad.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocalidadDto(
	@SerialName("IdLocalidad") val idLocalidad: String?,
	@SerialName("IdTipoLocalidad") val idTipoLocalidad: String?,
	@SerialName("IdAncestroPGrado") val idAncestroPGrado: String?,
	@SerialName("IdAncestroSGrado") val idAncestroSGrado: String?,
	@SerialName("NombreAncestroSGrado") val nombreAncestroSGrado: String?,
	@SerialName("IdAncestroTGrado") val idAncestroTGrado: String?,
	@SerialName("NombreAncestroTGrado") val nombreAncestroTGrado: String?,
	@SerialName("Nombre") val nombre: String?,
	@SerialName("NombreCorto") val nombreCorto: String?,
	@SerialName("NombreAncestroPGrado") val nombreAncestroPGrado: String?,
	@SerialName("NombreCompleto") val nombreCompleto: String?,
	@SerialName("NombreTipoLocalidad") val nombreTipoLocalidad: String?,
	@SerialName("AsignadoEnZona") val asignadoEnZona: Boolean?,
	@SerialName("AsignadoEnZonaOrig") val asignadoEnZonaOrig: Boolean?,
	@SerialName("DispoLocalidad") val dispoLocalidad: Boolean?,
	@SerialName("NombreZona") val nombreZona: String?,
	@SerialName("CodigoPostal") val codigoPostal: String?,
	@SerialName("Indicativo") val indicativo: String?,
	@SerialName("IdCentroServicio") val idCentroServicio: Int?,
	@SerialName("EstadoRegistro") val estadoRegistro: Int?,
	@SerialName("TiposLocalidades") val tiposLocalidades: String?,
	@SerialName("PermiteRecogida") val permiteRecogida: Boolean?,
	@SerialName("HoraMaxRecogida") val horaMaxRecogida: Int?,
	@SerialName("SeGeorreferencia") val seGeorreferencia: Boolean?,
	@SerialName("ValorRecogida") val valorRecogida: Double?,
	@SerialName("PermitePreEnviosPunto") val permitePreEnviosPunto: Boolean?,
	@SerialName("EtiquetaEntrega") val etiquetaEntrega: Boolean?,
	@SerialName("HoraMinRecogida") val horaMinRecogida: Int?,
	@SerialName("AbreviacionCiudad") val abreviacionCiudad: String?,
)
