package com.example.core.util

sealed interface DataError : Error {
	sealed interface Remote : DataError {
		data object BadRequest : Remote

		data object RequestTimeout : Remote

		data object Unauthorized : Remote

		data object Forbidden : Remote

		data object NotFound : Remote

		data object Conflict : Remote

		data object TooManyRequests : Remote

		data object ClientError : Remote

		data object NoInternet : Remote

		data object PayloadTooLarge : Remote

		data object ServerError : Remote

		data object ServiceUnavailable : Remote

		data object Serialization : Remote

		data object Unknown : Remote
	}

	sealed interface Local : DataError {
		data object DiskFull : Local

		data object DataCorruption : Local

		data object Unknown : Local
	}
}
