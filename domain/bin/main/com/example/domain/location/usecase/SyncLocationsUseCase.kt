package com.example.domain.location.usecase

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.domain.location.repository.LocationRepository
import javax.inject.Inject

class SyncLocationsUseCase
	@Inject
	constructor(
		private val repository: LocationRepository,
	) {
		suspend operator fun invoke(): EmptyResult<DataError> = repository.syncLocations()
	}
