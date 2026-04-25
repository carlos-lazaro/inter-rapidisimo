package com.example.data.auth.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.auth.local.entity.AuthEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {
	@Query("SELECT * FROM auth LIMIT 1")
	fun getAuth(): Flow<AuthEntity?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAuth(auth: AuthEntity)

	@Query("DELETE FROM auth")
	suspend fun deleteAll()

	@Transaction
	suspend fun replaceAuth(auth: AuthEntity) {
		deleteAll()
		insertAuth(auth)
	}
}
