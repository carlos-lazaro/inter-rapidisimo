package com.example.data.tabla.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.tabla.local.entity.TablaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TablaDao {
	@Query("SELECT * FROM tabla")
	fun getAllTablas(): Flow<List<TablaEntity>>

	@Query("SELECT * FROM tabla WHERE nombreTabla=:id")
	fun getTablaById(id: String): Flow<TablaEntity?>

	@Query("SELECT * FROM tabla WHERE nombreTabla IN (:tablasIds)")
	fun getTablaByIds(tablasIds: List<String>): Flow<List<TablaEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertTablas(tabla: List<TablaEntity>)

	@Query("DELETE FROM tabla")
	suspend fun deleteAll()

	@Transaction
	suspend fun replaceAll(tablas: List<TablaEntity>) {
		deleteAll()
		insertTablas(tablas)
	}
}
