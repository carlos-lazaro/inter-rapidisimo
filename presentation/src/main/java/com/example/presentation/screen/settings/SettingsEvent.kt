package com.example.presentation.screen.settings

import com.example.presentation.util.UiText

sealed interface SettingsEvent {
	data class ShowSnackbar(
		val message: UiText,
	) : SettingsEvent
}
