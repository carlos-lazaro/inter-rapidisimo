package com.example.data.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NetworkEntryPoint {
	fun okHttpClient(): OkHttpClient
}
