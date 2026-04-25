package com.example.presentation.util

import com.example.core.util.Error

sealed interface Resource<out T, out E : Error> {
	data class Success<out T>(
		val data: T,
		val loading: Boolean,
	) : Resource<T, Nothing>

	data class Failure<out E : Error>(
		val error: E,
	) : Resource<Nothing, E>

	data object Loading : Resource<Nothing, Nothing>

	data object Idle : Resource<Nothing, Nothing>
}

val Resource<*, *>.isIdle: Boolean
	get() = this is Resource.Idle

val Resource<*, *>.isLoading: Boolean
	get() =
		when (this) {
			Resource.Idle,
			is Resource.Failure,
			-> false

			Resource.Loading -> true

			is Resource.Success -> this.loading
		}

val Resource<*, *>.isSuccess: Boolean
	get() = this is Resource.Success

val Resource<*, *>.isFailure: Boolean
	get() = this is Resource.Failure

fun <T> Resource<T, *>.getDataOrNull(): T? = (this as? Resource.Success)?.data

inline fun <T> Resource<T, *>.onSuccess(action: (T) -> Unit): Resource<T, *> {
	if (this is Resource.Success) action(data)
	return this
}

inline fun <T, E : Error> Resource<T, E>.onError(action: (E) -> Unit): Resource<T, E> {
	if (this is Resource.Failure) action(error)
	return this
}
