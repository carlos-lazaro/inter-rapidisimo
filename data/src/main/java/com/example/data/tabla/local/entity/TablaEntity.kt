package com.example.data.tabla.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla")
data class TablaEntity(
	@PrimaryKey
	val nombreTabla: String,
	val pk: String?,
	val queryCreacion: String?,
	val batchSize: Int?,
	val filtro: String?,
	val error: String?,
	val numeroCampos: Int?,
	val metodoApp: String?,
	val fechaActualizacionSincro: String?,
)
