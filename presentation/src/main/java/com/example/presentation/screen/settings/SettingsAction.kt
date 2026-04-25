package com.example.presentation.screen.settings

sealed interface SettingsAction {
	data object SetAppVersion : SettingsAction

	data object OpenEditAppVersion : SettingsAction

	data object CloseEditAppVersion : SettingsAction
}
