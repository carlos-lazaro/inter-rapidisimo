package com.example.presentation.screen.home

import com.example.presentation.util.UiText

sealed interface HomeEvent {
	data class ShowSnackbar(
		val message: UiText,
	) : HomeEvent
}
