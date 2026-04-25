package com.example.domain.error

import com.example.core.util.DataError
import com.example.core.util.Error
import com.example.core.util.Result

sealed interface DomainError : Error {
	data object UsernameEmpty : DomainError

	data object PasswordEmpty : DomainError

	data object InvalidAppVersion : DomainError

	data object AppVersionOutdated : DomainError

	data object Unknown : DomainError

	data class LocalError(
		val error: DataError.Local,
	) : DomainError

	data class RemoteError(
		val error: DataError.Remote,
	) : DomainError
}

fun DomainError.asResultFailure() = Result.Failure(this)
