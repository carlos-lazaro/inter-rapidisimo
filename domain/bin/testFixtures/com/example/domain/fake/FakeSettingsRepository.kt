package com.example.domain.fake

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.setting.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeSettingsRepository(
	appVersion: Int = 0,
) : SettingsRepository {
	private val _appVersion = MutableStateFlow(appVersion)

	override val appVersion: Flow<Int> = _appVersion.asStateFlow()

	override suspend fun setAppVersion(value: Int): EmptyResult<DataError.Local> {
		_appVersion.value = value
		return Result.Success(Unit)
	}
}
