package com.example.data.util

import com.example.core.util.DataError
import com.example.core.util.Result
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<out T?>): Result<T, DataError.Remote> {
	return try {
		val response = call.invoke()

		if (response.isSuccessful) {
			val body = response.body()

			return if (body != null) {
				Result.Success(body)
			} else {
				Result.Failure(DataError.Remote.Serialization)
			}
		}

		Timber.e("Network Error Code: ${response.code()}")

		val error =
			when (response.code()) {
				400 -> DataError.Remote.BadRequest
				401 -> DataError.Remote.Unauthorized
				403 -> DataError.Remote.Forbidden
				404 -> DataError.Remote.NotFound
				408 -> DataError.Remote.RequestTimeout
				409 -> DataError.Remote.Conflict
				413 -> DataError.Remote.PayloadTooLarge
				429 -> DataError.Remote.TooManyRequests
				503 -> DataError.Remote.ServiceUnavailable
				in 400..499 -> DataError.Remote.ClientError
				in 500..599 -> DataError.Remote.ServerError
				else -> DataError.Remote.Unknown
			}

		Result.Failure(error)
	} catch (e: UnknownHostException) {
		Timber.e(e, "Network Exception: UnknownHostException")
		Result.Failure(DataError.Remote.NoInternet)
	} catch (e: UnresolvedAddressException) {
		Timber.e(e, "Network Exception: UnresolvedAddressException")
		Result.Failure(DataError.Remote.NoInternet)
	} catch (e: ConnectException) {
		Timber.e(e, "Network Exception: ConnectException")
		Result.Failure(DataError.Remote.NoInternet)
	} catch (e: SocketTimeoutException) {
		Timber.e(e, "Network Exception: SocketTimeoutException")
		Result.Failure(DataError.Remote.RequestTimeout)
	} catch (e: SerializationException) {
		Timber.e(e, "Network Exception: SerializationException")
		Result.Failure(DataError.Remote.Serialization)
	} catch (e: IOException) {
		Timber.e(e, "Network Exception: IOException")
		Result.Failure(DataError.Remote.NoInternet)
	} catch (e: Exception) {
		currentCoroutineContext().ensureActive()
		Timber.e(e, "Unknown network error")
		Result.Failure(DataError.Remote.Unknown)
	}
}
