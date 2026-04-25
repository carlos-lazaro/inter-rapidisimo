package com.example.domain.security.repository

import com.example.core.util.DataError
import com.example.core.util.Result
import com.example.domain.security.model.SecurityConfig

interface SecurityRepository {
	suspend fun getConfig(): Result<SecurityConfig, DataError.Remote>
}
