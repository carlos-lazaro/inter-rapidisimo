package com.example.base

import android.app.Application
import android.os.StrictMode
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import com.example.core.coroutines.DispatchersProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication :
	Application(),
	SingletonImageLoader.Factory {
	@Inject
	lateinit var dispatchersProvider: DispatchersProvider

	override fun onCreate() {
		super.onCreate()

		val isDebug = BuildConfig.DEBUG
		if (isDebug) {
			setupStrictMode()
		}

		SingletonImageLoader.get(this)
	}

	override fun newImageLoader(context: PlatformContext): ImageLoader =
		ImageLoader
			.Builder(context)
			.coroutineContext(dispatchersProvider.io)
			.memoryCache {
				MemoryCache
					.Builder()
					.maxSizePercent(context, 0.2)
					.build()
			}.diskCache {
				DiskCache
					.Builder()
					.directory(context.cacheDir.resolve("image_cache"))
					.maxSizeBytes(50 * 1024 * 1024)
					.build()
			}.diskCachePolicy(CachePolicy.ENABLED)
			.memoryCachePolicy(CachePolicy.ENABLED)
			.build()

	private fun setupStrictMode() {
		StrictMode.setThreadPolicy(
			StrictMode.ThreadPolicy
				.Builder()
				.detectCustomSlowCalls()
				.detectDiskReads()
				.detectDiskWrites()
				.detectNetwork()
				.penaltyLog()
				.build(),
		)
		StrictMode.setVmPolicy(
			StrictMode.VmPolicy
				.Builder()
				.detectActivityLeaks()
				.detectLeakedSqlLiteObjects()
				.detectLeakedClosableObjects()
				.penaltyLog()
				.build(),
		)
	}
}
