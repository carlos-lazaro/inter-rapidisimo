package com.example.presentation

sealed interface SessionState {
	data object Loading : SessionState

	data object LoggedIn : SessionState

	data object LoggedOut : SessionState
}
