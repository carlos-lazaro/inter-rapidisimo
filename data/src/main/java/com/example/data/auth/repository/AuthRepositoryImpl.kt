package com.example.data.auth.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.core.util.onFailure
import com.example.data.auth.local.AuthLocalDataSource
import com.example.data.auth.mapper.toDomain
import com.example.data.auth.mapper.toEntity
import com.example.data.auth.mapper.toRequest
import com.example.data.auth.remote.AuthRemoteDataSource
import com.example.domain.auth.model.AuthHeaders
import com.example.domain.auth.model.User
import com.example.domain.auth.model.UserForm
import com.example.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl
	@Inject
	constructor(
		private val remoteDataSource: AuthRemoteDataSource,
		private val localDataSource: AuthLocalDataSource,
	) : AuthRepository {
		override suspend fun login(
			userForm: UserForm,
			headers: AuthHeaders,
		): Result<User, DataError.Remote> =
			when (
				val result =
					remoteDataSource.login(
						request = userForm.toRequest(),
						headers = headers,
					)
			) {
				is Result.Failure -> {
					result
				}

				is Result.Success -> {
					val entity =
						result.data.toEntity()
							?: return Result.Failure(DataError.Remote.Serialization)
					val user =
						entity.toDomain()
							?: return Result.Failure(DataError.Remote.Serialization)
					localDataSource
						.replaceAuth(entity)
						.onFailure { Timber.e("Failed to persist auth entity: $it") }
					Result.Success(user)
				}
			}

		override fun getAuthUser(): Flow<User?> = localDataSource.getAuth().map { it?.toDomain() }

		override suspend fun logout(): EmptyResult<DataError.Local> = localDataSource.clearAuth()
	}
