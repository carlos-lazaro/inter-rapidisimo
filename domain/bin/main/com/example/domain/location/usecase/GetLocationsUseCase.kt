package com.example.domain.location.usecase

import com.example.domain.location.model.Location
import com.example.domain.location.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationsUseCase
	@Inject
	constructor(
		private val repository: LocationRepository,
	) {
		operator fun invoke(): Flow<List<Location>> = repository.getLocations()
	}
