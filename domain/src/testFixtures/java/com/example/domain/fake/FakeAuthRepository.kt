package com.example.domain.fake

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.auth.model.AuthHeaders
import com.example.domain.auth.model.User
import com.example.domain.auth.model.UserForm
import com.example.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeAuthRepository(
	initialUser: User? = null,
) : AuthRepository {
	private val _user = MutableStateFlow(initialUser)

	private var loginResult: Result<User, DataError> = Result.Success(initialUser ?: User("", null, null))
	private var logoutResult: EmptyResult<DataError.Local> = Result.Success(Unit)

	fun emit(user: User?) = _user.update { user }

	fun clear() = _user.update { null }

	fun getUser() = _user.value

	fun setLoginResult(result: Result<User, DataError>) {
		loginResult = result
	}

	fun setLoginSuccess(user: User) {
		loginResult = Result.Success(user)
	}

	fun setLoginError(error: DataError) {
		loginResult = Result.Failure(error)
	}

	fun setLogoutResult(result: EmptyResult<DataError.Local>) {
		logoutResult = result
	}

	fun setLogoutSuccess() {
		logoutResult = Result.Success(Unit)
	}

	fun setLogoutError(error: DataError.Local) {
		logoutResult = Result.Failure(error)
	}

	var capturedUserForm: UserForm? = null
		private set

	override suspend fun login(
		userForm: UserForm,
		headers: AuthHeaders,
	): Result<User, DataError> {
		capturedUserForm = userForm
		return loginResult
	}

	override fun getAuthUser(): Flow<User?> = _user

	override suspend fun logout(): EmptyResult<DataError.Local> = logoutResult
}
