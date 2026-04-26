package com.example.presentation.screen.settings

import androidx.compose.foundation.text.input.TextFieldState
import com.example.presentation.util.UiText

data class SettingsState(
	val appVersion: Int = 0,
	val isLoading: Boolean = false,
	val openSheetEditAppVersion: Boolean = false,
	val versionInput: TextFieldState = TextFieldState(),
	val versionInputError: UiText? = null,
)
