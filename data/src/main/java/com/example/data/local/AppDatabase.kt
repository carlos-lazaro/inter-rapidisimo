package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.auth.local.dao.AuthDao
import com.example.data.auth.local.entity.AuthEntity
import com.example.data.localidad.local.dao.LocalidadDao
import com.example.data.localidad.local.entity.LocalidadEntity
import com.example.data.tabla.local.dao.TablaDao
import com.example.data.tabla.local.entity.TablaEntity

@Database(
	entities = [
		TablaEntity::class,
		LocalidadEntity::class,
		AuthEntity::class,
	],
	version = 1,
	exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun tablaDao(): TablaDao

	abstract fun localidadDao(): LocalidadDao

	abstract fun authDao(): AuthDao
}
