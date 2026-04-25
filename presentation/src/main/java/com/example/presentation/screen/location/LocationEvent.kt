package com.example.presentation.screen.location

import com.example.presentation.util.UiText

sealed interface LocationEvent {
	data class ShowSnackbar(
		val message: UiText,
	) : LocationEvent
}
