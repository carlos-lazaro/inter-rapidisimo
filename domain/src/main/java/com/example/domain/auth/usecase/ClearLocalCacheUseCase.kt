package com.example.domain.auth.usecase

import com.example.domain.location.repository.LocationRepository
import com.example.domain.table.repository.TableRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClearLocalCacheUseCase
	@Inject
	constructor(
		private val locationRepository: LocationRepository,
		private val tableRepository: TableRepository,
		private val scope: CoroutineScope,
	) {
		operator fun invoke() {
			scope.launch {
				locationRepository.clearLocations()
			}
			scope.launch {
				tableRepository.clearTables()
			}
		}
	}
