package com.example.data.tabla.mapper

import com.example.data.tabla.local.entity.TablaEntity
import com.example.data.tabla.remote.dto.TablaDto
import com.example.domain.table.model.Table

fun TablaDto.toEntity(): TablaEntity? {
	if (nombreTabla.isNullOrBlank()) {
		return null
	}

	return TablaEntity(
		nombreTabla = nombreTabla,
		pk = pk,
		queryCreacion = queryCreacion,
		batchSize = batchSize,
		filtro = filtro,
		error = error,
		numeroCampos = numeroCampos,
		metodoApp = metodoApp,
		fechaActualizacionSincro = fechaActualizacionSincro,
	)
}

fun TablaEntity.toDomain(): Table? {
	if (nombreTabla.isBlank()) return null
	return Table(tableName = nombreTabla, pk = pk ?: "", batchSize = batchSize ?: 0)
}

fun List<TablaEntity>.toDomain(): List<Table> = mapNotNull { it.toDomain() }
