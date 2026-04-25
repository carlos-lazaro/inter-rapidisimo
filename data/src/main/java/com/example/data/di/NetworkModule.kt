package com.example.data.di

import com.example.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
	@Provides
	@Singleton
	@Named("baseUrl")
	fun provideBaseUrl(): String = "https://apitesting.interrapidisimo.co/"

	@Provides
	@Singleton
	fun provideJson(): Json =
		Json {
			ignoreUnknownKeys = true
			isLenient = true
			coerceInputValues = true
		}

	@Provides
	@Singleton
	fun provideLoggingInterceptor(): HttpLoggingInterceptor =
		HttpLoggingInterceptor().apply {
			level =
				if (BuildConfig.DEBUG) {
					HttpLoggingInterceptor.Level.BODY
				} else {
					HttpLoggingInterceptor.Level.NONE
				}
		}

	@Provides
	@Singleton
	fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
		val builder = OkHttpClient.Builder()

		if (BuildConfig.DEBUG) {
			builder.addInterceptor(loggingInterceptor)
		}

		return builder
			.connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)
			.writeTimeout(30, TimeUnit.SECONDS)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
		json: Json,
		@Named("baseUrl") baseUrl: String,
	): Retrofit {
		val contentType = "application/json".toMediaType()

		return Retrofit
			.Builder()
			.baseUrl(baseUrl)
			.client(okHttpClient)
			.addConverterFactory(ScalarsConverterFactory.create())
			.addConverterFactory(json.asConverterFactory(contentType))
			.build()
	}
}
