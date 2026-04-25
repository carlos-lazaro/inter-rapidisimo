package com.example.data.localidad.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localidad")
data class LocalidadEntity(
	@PrimaryKey
	val idLocalidad: String,
	val idTipoLocalidad: String?,
	val idAncestroPGrado: String?,
	val idAncestroSGrado: String?,
	val nombreAncestroSGrado: String?,
	val idAncestroTGrado: String?,
	val nombreAncestroTGrado: String?,
	val nombre: String?,
	val nombreCorto: String?,
	val nombreAncestroPGrado: String?,
	val nombreCompleto: String?,
	val nombreTipoLocalidad: String?,
	val asignadoEnZona: Boolean?,
	val asignadoEnZonaOrig: Boolean?,
	val dispoLocalidad: Boolean?,
	val nombreZona: String?,
	val codigoPostal: String?,
	val indicativo: String?,
	val idCentroServicio: Int?,
	val estadoRegistro: Int?,
	val tiposLocalidades: String?,
	val permiteRecogida: Boolean?,
	val horaMaxRecogida: Int?,
	val seGeorreferencia: Boolean?,
	val valorRecogida: Double?,
	val permitePreEnviosPunto: Boolean?,
	val etiquetaEntrega: Boolean?,
	val horaMinRecogida: Int?,
	val abreviacionCiudad: String?,
)
