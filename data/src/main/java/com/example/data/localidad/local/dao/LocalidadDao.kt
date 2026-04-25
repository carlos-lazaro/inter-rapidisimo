package com.example.data.localidad.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.localidad.local.entity.LocalidadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalidadDao {
	@Query("SELECT * FROM localidad")
	fun getAllLocalidades(): Flow<List<LocalidadEntity>>

	@Query("SELECT * FROM localidad WHERE idLocalidad=:id")
	fun getLocalidadById(id: String): Flow<LocalidadEntity?>

	@Query("SELECT * FROM localidad WHERE idLocalidad IN (:localidadesIds)")
	fun getLocalidadByIds(localidadesIds: List<String>): Flow<List<LocalidadEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertLocalidades(localidad: List<LocalidadEntity>)

	@Query("DELETE FROM localidad")
	suspend fun deleteAll()

	@Transaction
	suspend fun replaceAll(localidades: List<LocalidadEntity>) {
		deleteAll()
		insertLocalidades(localidades)
	}
}
