package com.example.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.setting.model.ThemeMode
import com.example.domain.setting.repository.SettingsRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingsRepositoryImpl
	@Inject
	constructor(
		private val dataStore: DataStore<Preferences>,
	) : SettingsRepository {
		companion object {
			private val APP_VERSION_KEY = intPreferencesKey("APP_VERSION_KEY")
		}

		private val dataStoreFlow: Flow<Preferences> =
			dataStore.data.catch { exception ->
				if (exception is IOException) emit(emptyPreferences()) else throw exception
			}

		override val appVersion: Flow<Int> =
			dataStoreFlow.map { preferences -> preferences[APP_VERSION_KEY] ?: 0 }

		override suspend fun setAppVersion(value: Int): EmptyResult<DataError.Local> = safeDataStoreEdit { it[APP_VERSION_KEY] = value }

		private suspend fun safeDataStoreEdit(block: (MutablePreferences) -> Unit): EmptyResult<DataError.Local> =
			try {
				dataStore.edit { block(it) }
				Result.Success(Unit)
			} catch (e: IOException) {
				Result.Failure(DataError.Local.Unknown)
			} catch (e: Exception) {
				currentCoroutineContext().ensureActive()
				Result.Failure(DataError.Local.Unknown)
			}
	}
