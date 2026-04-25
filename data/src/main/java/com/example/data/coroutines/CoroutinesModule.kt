package com.example.data.coroutines

import com.example.core.coroutines.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutinesModule {
	@Binds
	@Singleton
	abstract fun bindDispatchersProvider(impl: DispatchersProviderImpl): DispatchersProvider
}
