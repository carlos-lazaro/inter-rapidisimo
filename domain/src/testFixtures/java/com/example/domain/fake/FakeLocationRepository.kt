package com.example.domain.fake

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.location.model.Location
import com.example.domain.location.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeLocationRepository(
	initialLocations: List<Location> = emptyList(),
) : LocationRepository {
	private val _locations = MutableStateFlow(initialLocations)

	private var syncResult: EmptyResult<DataError> = Result.Success(Unit)
	private var clearResult: EmptyResult<DataError.Local> = Result.Success(Unit)

	fun emit(vararg locations: Location) = _locations.update { locations.toList() }

	fun emitList(locations: List<Location>) = _locations.update { locations }

	fun clear() = _locations.update { emptyList() }

	fun getLocationsSync() = _locations.value

	fun setSyncResult(result: EmptyResult<DataError>) {
		syncResult = result
	}

	fun setSyncSuccess() {
		syncResult = Result.Success(Unit)
	}

	fun setSyncError(error: DataError) {
		syncResult = Result.Failure(error)
	}

	fun setClearResult(result: EmptyResult<DataError.Local>) {
		clearResult = result
	}

	fun setClearSuccess() {
		clearResult = Result.Success(Unit)
	}

	fun setClearError(error: DataError.Local) {
		clearResult = Result.Failure(error)
	}

	override fun getLocations(): Flow<List<Location>> = _locations

	override suspend fun syncLocations(): EmptyResult<DataError> = syncResult

	override suspend fun clearLocations(): EmptyResult<DataError.Local> = clearResult
}
