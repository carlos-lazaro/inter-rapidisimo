package com.example.domain.fake

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.security.model.SecurityConfig
import com.example.domain.security.repository.SecurityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSecurityRepository(
	private val initialConfig: SecurityConfig = SecurityConfig(minimumVersion = 1),
) : SecurityRepository {
	private var getConfigResult: Result<SecurityConfig, DataError.Remote> = Result.Success(initialConfig)

	fun setGetConfigResult(result: Result<SecurityConfig, DataError.Remote>) {
		getConfigResult = result
	}

	fun setGetConfigSuccess(config: SecurityConfig) {
		getConfigResult = Result.Success(config)
	}

	fun setGetConfigError(error: DataError.Remote) {
		getConfigResult = Result.Failure(error)
	}

	override suspend fun getConfig(): Result<SecurityConfig, DataError.Remote> = getConfigResult
}
