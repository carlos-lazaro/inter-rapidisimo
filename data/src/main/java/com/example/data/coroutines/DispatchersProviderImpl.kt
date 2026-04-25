package com.example.data.coroutines

import com.example.core.coroutines.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatchersProviderImpl
	@Inject
	constructor() : DispatchersProvider {
		override val main: CoroutineDispatcher = Dispatchers.Main
		override val io: CoroutineDispatcher = Dispatchers.IO
		override val default: CoroutineDispatcher = Dispatchers.Default
	}
