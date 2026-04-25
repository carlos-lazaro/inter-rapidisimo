package com.example.presentation.screen.login

import com.example.presentation.util.UiText

sealed interface LoginEvent {
	data class ShowSnackbar(
		val message: UiText,
	) : LoginEvent

	data object NavigateToHome : LoginEvent
}
