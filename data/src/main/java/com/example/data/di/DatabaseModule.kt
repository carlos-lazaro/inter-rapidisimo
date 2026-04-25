package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.auth.local.dao.AuthDao
import com.example.data.local.AppDatabase
import com.example.data.localidad.local.dao.LocalidadDao
import com.example.data.tabla.local.dao.TablaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
	@Provides
	@Singleton
	fun provideAppDatabase(
		@ApplicationContext context: Context,
	): AppDatabase =
		Room
			.databaseBuilder(
				context,
				AppDatabase::class.java,
				"app_db",
			).build()

	@Provides
	@Singleton
	fun provideTablaDao(db: AppDatabase): TablaDao = db.tablaDao()

	@Provides
	@Singleton
	fun provideLocalidadDao(db: AppDatabase): LocalidadDao = db.localidadDao()

	@Provides
	@Singleton
	fun provideAuthDao(db: AppDatabase): AuthDao = db.authDao()
}
