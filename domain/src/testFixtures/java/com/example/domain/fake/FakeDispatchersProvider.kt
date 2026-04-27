@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.domain.fake

import com.example.core.coroutines.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class FakeDispatchersProvider : DispatchersProvider {
	private val dispatcher = UnconfinedTestDispatcher()

	override val main: CoroutineDispatcher = dispatcher

	override val io: CoroutineDispatcher = dispatcher

	override val default: CoroutineDispatcher = dispatcher
}
