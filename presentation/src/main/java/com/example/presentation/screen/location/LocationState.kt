package com.example.presentation.screen.location

import com.example.domain.location.model.Location

data class LocationState(
	val locations: List<Location> = emptyList(),
	val isLoading: Boolean = false,
)
