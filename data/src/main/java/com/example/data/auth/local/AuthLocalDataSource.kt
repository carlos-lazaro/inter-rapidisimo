package com.example.data.auth.local

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.data.auth.local.dao.AuthDao
import com.example.data.auth.local.entity.AuthEntity
import com.example.data.util.safeLocalCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthLocalDataSource
	@Inject
	constructor(
		private val dao: AuthDao,
	) {
		fun getAuth(): Flow<AuthEntity?> = dao.getAuth()

		suspend fun replaceAuth(auth: AuthEntity): EmptyResult<DataError.Local> = safeLocalCall { dao.replaceAuth(auth) }

		suspend fun clearAuth(): EmptyResult<DataError.Local> = safeLocalCall { dao.deleteAll() }
	}
