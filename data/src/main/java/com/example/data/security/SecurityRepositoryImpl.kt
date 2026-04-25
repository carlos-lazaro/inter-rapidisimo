package com.example.data.security

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.core.util.map
import com.example.data.util.safeApiCall
import com.example.domain.security.model.SecurityConfig
import com.example.domain.security.repository.SecurityRepository
import javax.inject.Inject

class SecurityRepositoryImpl
	@Inject
	constructor(
		private val api: SecurityApiService,
	) : SecurityRepository {
		override suspend fun getConfig(): Result<SecurityConfig, DataError.Remote> =
			safeApiCall { api.vpStoreAppControl() }
				.map { versionString -> SecurityConfig(minimumVersion = versionString.trim('"').toIntOrNull() ?: -1) }
	}
