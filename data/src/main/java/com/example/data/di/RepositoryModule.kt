package com.example.data.di

import com.example.data.auth.repository.AuthRepositoryImpl
import com.example.data.localidad.repository.LocationRepositoryImpl
import com.example.data.security.SecurityRepositoryImpl
import com.example.data.settings.SettingsRepositoryImpl
import com.example.data.tabla.repository.TableRepositoryImpl
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.location.repository.LocationRepository
import com.example.domain.security.repository.SecurityRepository
import com.example.domain.setting.repository.SettingsRepository
import com.example.domain.table.repository.TableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
	@Binds
	@Singleton
	abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

	@Binds
	@Singleton
	abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

	@Binds
	@Singleton
	abstract fun bindSecurityRepository(impl: SecurityRepositoryImpl): SecurityRepository

	@Binds
	@Singleton
	abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

	@Binds
	@Singleton
	abstract fun bindTableRepository(impl: TableRepositoryImpl): TableRepository
}
