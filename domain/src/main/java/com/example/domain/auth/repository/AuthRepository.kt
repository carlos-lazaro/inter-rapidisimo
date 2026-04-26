package com.example.domain.auth.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.auth.model.AuthHeaders
import com.example.domain.auth.model.User
import com.example.domain.auth.model.UserForm
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
	suspend fun login(
		userForm: UserForm,
		headers: AuthHeaders,
	): Result<User, DataError>

	fun getAuthUser(): Flow<User?>

	suspend fun logout(): EmptyResult<DataError.Local>
}
