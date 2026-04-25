package com.example.domain.setting.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
	val appVersion: Flow<Int>

	suspend fun setAppVersion(value: Int): EmptyResult<DataError.Local>
}
