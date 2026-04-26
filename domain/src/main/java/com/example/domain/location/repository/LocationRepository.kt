package com.example.domain.location.repository

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.domain.location.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
	fun getLocations(): Flow<List<Location>>

	suspend fun syncLocations(): EmptyResult<DataError>

	suspend fun clearLocations(): EmptyResult<DataError.Local>
}
