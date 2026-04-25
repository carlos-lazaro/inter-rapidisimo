package com.example.data.auth.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
data class AuthEntity(
	@PrimaryKey val usuario: String,
	val identificacion: String?,
	val nombre: String?,
	val apellido1: String?,
	val apellido2: String?,
	val cargo: String?,
	val aplicaciones: String?,
	val ubicaciones: String?,
	val mensajeResultado: Int?,
	val idLocalidad: String?,
	val nombreLocalidad: String?,
	val nomRol: String?,
	val idRol: String?,
	val tokenJWT: String?,
	val modulosApp: String?,
	val cachedAt: Long = System.currentTimeMillis(),
)
