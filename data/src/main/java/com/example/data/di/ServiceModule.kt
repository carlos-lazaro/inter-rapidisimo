package com.example.data.di

import com.example.data.auth.remote.AuthApiService
import com.example.data.localidad.remote.LocalidadApiService
import com.example.data.security.SecurityApiService
import com.example.data.tabla.remote.TablaApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
	@Provides
	@Singleton
	fun provideAuthApiService(retrofit: Retrofit): AuthApiService = retrofit.create(AuthApiService::class.java)

	@Provides
	@Singleton
	fun provideSecurityApiService(retrofit: Retrofit): SecurityApiService = retrofit.create(SecurityApiService::class.java)

	@Provides
	@Singleton
	fun provideTablaApiService(retrofit: Retrofit): TablaApiService = retrofit.create(TablaApiService::class.java)

	@Provides
	@Singleton
	fun provideLocalidadApiService(retrofit: Retrofit): LocalidadApiService = retrofit.create(LocalidadApiService::class.java)
}
