package com.example.presentation.screen.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.onFailure
import com.example.core.util.onSuccess
import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.error.DomainError
import com.example.domain.security.usecase.CheckMinimumVersionUseCase
import com.example.presentation.BuildConfig
import com.example.presentation.R
import com.example.presentation.util.UiText
import com.example.presentation.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
	@Inject
	constructor(
		private val loginUseCase: LoginUseCase,
		private val checkMinimumVersionUseCase: CheckMinimumVersionUseCase,
	) : ViewModel() {
		private var hasLoadedInitialData = false

		private val _state =
			MutableStateFlow(
				if (BuildConfig.DEBUG) {
//                    TODO: for quick testing and just for development purposes
					LoginState(
						username = TextFieldState("pam.meredy21"),
						password = TextFieldState("Inter2021"),
					)
				} else {
					LoginState()
				},
			)
		val state =
			_state
				.onStart {
					if (!hasLoadedInitialData) {
						checkMinimumVersion()
						hasLoadedInitialData = true
					}
				}.stateIn(
					scope = viewModelScope,
					started = SharingStarted.WhileSubscribed(5_000L),
					initialValue = _state.value,
				)

		private val _events = Channel<LoginEvent>()
		val events = _events.receiveAsFlow()

		fun onAction(action: LoginAction) {
			when (action) {
				LoginAction.Submit -> login()
				LoginAction.TogglePasswordVisible -> _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
			}
		}

		private fun checkMinimumVersion() {
			viewModelScope.launch {
				checkMinimumVersionUseCase()
					.onSuccess {
						_events.send(LoginEvent.ShowSnackbar(UiText.StringResource(R.string.app_version_updated)))
					}.onFailure { error ->
						_events.send(LoginEvent.ShowSnackbar(error.asUiText()))
					}
			}
		}

		private fun login() {
			if (_state.value.isLoading) return
			val username =
				_state.value.username.text
					.toString()
			val password =
				_state.value.password.text
					.toString()

			viewModelScope.launch {
				_state.update { it.copy(isLoading = true) }
				try {
					loginUseCase(
						usuario = username,
						password = password,
					).onSuccess {
						_events.send(LoginEvent.LoginSuccess)
					}.onFailure { error ->
						when (error) {
							DomainError.UsernameEmpty -> {
								_state.update { it.copy(usernameError = error.asUiText(), passwordError = null) }
							}

							DomainError.PasswordEmpty -> {
								_state.update { it.copy(usernameError = null, passwordError = error.asUiText()) }
							}

							else -> {
								_events.send(LoginEvent.ShowSnackbar(error.asUiText()))
							}
						}
					}
				} finally {
					_state.update { it.copy(isLoading = false) }
				}
			}
		}
	}
