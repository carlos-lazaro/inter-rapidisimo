package com.example.base.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.data.di.NetworkEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NetworkInitializer : Initializer<Unit> {
	override fun create(context: Context) {
		CoroutineScope(Dispatchers.IO).launch {
			val entryPoint =
				EntryPointAccessors.fromApplication(
					context.applicationContext,
					NetworkEntryPoint::class.java,
				)

			entryPoint.okHttpClient()
		}
	}

	override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
