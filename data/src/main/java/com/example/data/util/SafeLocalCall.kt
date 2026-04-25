package com.example.data.util

import android.database.sqlite.SQLiteDatabaseCorruptException
import android.database.sqlite.SQLiteFullException
import com.example.core.util.DataError
import com.example.core.util.Result
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import timber.log.Timber

suspend fun <T> safeLocalCall(call: suspend () -> T): Result<T, DataError.Local> =
	try {
		val result = call.invoke()
		Result.Success(result)
	} catch (e: SQLiteFullException) {
		Timber.e(e, "Local DB Error: Disk is full.")
		Result.Failure(DataError.Local.DiskFull)
	} catch (e: SQLiteDatabaseCorruptException) {
		Timber.e(e, "Local DB Error: Database file is corrupted.")
		Result.Failure(DataError.Local.DataCorruption)
	} catch (e: Exception) {
		currentCoroutineContext().ensureActive()

		Timber.e(e, "Local DB Error: Unexpected exception.")
		Result.Failure(DataError.Local.Unknown)
	}
