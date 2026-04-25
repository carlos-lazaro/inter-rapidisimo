package com.example.presentation.screen.login

sealed interface LoginAction {
	data object TogglePasswordVisible : LoginAction

	data object Submit : LoginAction
}
