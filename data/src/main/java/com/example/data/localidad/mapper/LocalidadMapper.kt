package com.example.data.localidad.mapper

import com.example.data.localidad.local.entity.LocalidadEntity
import com.example.data.localidad.remote.dto.LocalidadDto
import com.example.domain.location.model.Location

fun LocalidadEntity.toDomain(): Location? {
	if (idLocalidad.isBlank()) return null
	return Location(
		idLocalidad = idLocalidad,
		nombre = nombre,
		nombreCorto = nombreCorto,
		nombreZona = nombreZona,
		codigoPostal = codigoPostal,
		nombreCompleto = nombreCompleto,
		abreviacionCiudad = abreviacionCiudad,
	)
}

fun List<LocalidadEntity>.toDomain(): List<Location> = mapNotNull { it.toDomain() }

fun LocalidadDto.toEntity(): LocalidadEntity? {
	if (idLocalidad.isNullOrBlank()) {
		return null
	}

	return LocalidadEntity(
		idLocalidad = idLocalidad,
		idTipoLocalidad = idTipoLocalidad,
		idAncestroPGrado = idAncestroPGrado,
		idAncestroSGrado = idAncestroSGrado,
		nombreAncestroSGrado = nombreAncestroSGrado,
		idAncestroTGrado = idAncestroTGrado,
		nombreAncestroTGrado = nombreAncestroTGrado,
		nombre = nombre,
		nombreCorto = nombreCorto,
		nombreAncestroPGrado = nombreAncestroPGrado,
		nombreCompleto = nombreCompleto,
		nombreTipoLocalidad = nombreTipoLocalidad,
		asignadoEnZona = asignadoEnZona,
		asignadoEnZonaOrig = asignadoEnZonaOrig,
		dispoLocalidad = dispoLocalidad,
		nombreZona = nombreZona,
		codigoPostal = codigoPostal,
		indicativo = indicativo,
		idCentroServicio = idCentroServicio,
		estadoRegistro = estadoRegistro,
		tiposLocalidades = tiposLocalidades,
		permiteRecogida = permiteRecogida,
		horaMaxRecogida = horaMaxRecogida,
		seGeorreferencia = seGeorreferencia,
		valorRecogida = valorRecogida,
		permitePreEnviosPunto = permitePreEnviosPunto,
		etiquetaEntrega = etiquetaEntrega,
		horaMinRecogida = horaMinRecogida,
		abreviacionCiudad = abreviacionCiudad,
	)
}

fun List<LocalidadDto>.toEntity(): List<LocalidadEntity> = mapNotNull { it.toEntity() }
