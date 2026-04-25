package com.example.presentation.screen.login

import androidx.compose.foundation.text.input.TextFieldState
import com.example.presentation.util.UiText

data class LoginState(
	val username: TextFieldState = TextFieldState(),
	val usernameError: UiText? = null,
	val password: TextFieldState = TextFieldState(),
	val passwordError: UiText? = null,
	val isPasswordVisible: Boolean = false,
	val isLoading: Boolean = false,
) {
	val hasUsernameError: Boolean
		get() = usernameError != null

	val hasPasswordError: Boolean
		get() = passwordError != null
}
