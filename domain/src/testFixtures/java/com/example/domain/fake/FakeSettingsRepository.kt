package com.example.domain.fake

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.setting.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeSettingsRepository(
	initialAppVersion: Int = 0,
) : SettingsRepository {
	private val _appVersion = MutableStateFlow(initialAppVersion)

	fun updateAppVersion(value: Int) = _appVersion.update { value }

	fun getAppVersion() = _appVersion.value

	private var setAppVersionResult: EmptyResult<DataError.Local> = Result.Success(Unit)

	fun setSetAppVersionResult(result: EmptyResult<DataError.Local>) {
		setAppVersionResult = result
	}

	fun setSetAppVersionSuccess() {
		setAppVersionResult = Result.Success(Unit)
	}

	fun setSetAppVersionError(error: DataError.Local) {
		setAppVersionResult = Result.Failure(error)
	}

	override val appVersion: Flow<Int> = _appVersion.asStateFlow()

	override suspend fun setAppVersion(value: Int): EmptyResult<DataError.Local> {
		_appVersion.update { value }
		return setAppVersionResult
	}
}
