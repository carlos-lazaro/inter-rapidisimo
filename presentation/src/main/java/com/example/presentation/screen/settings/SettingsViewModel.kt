package com.example.presentation.screen.settings

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.onFailure
import com.example.core.util.onSuccess
import com.example.domain.error.DomainError
import com.example.domain.setting.usecase.GetAppVersionUseCase
import com.example.domain.setting.usecase.SetAppVersionUseCase
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
class SettingsViewModel
	@Inject
	constructor(
		private val getAppVersionUseCase: GetAppVersionUseCase,
		private val setAppVersionUseCase: SetAppVersionUseCase,
	) : ViewModel() {
		private var hasLoadedInitialData = false

		private val _state = MutableStateFlow(SettingsState())
		val state =
			_state
				.onStart {
					if (!hasLoadedInitialData) {
						observeAppVersion()
						hasLoadedInitialData = true
					}
				}.stateIn(
					scope = viewModelScope,
					started = SharingStarted.WhileSubscribed(5_000L),
					initialValue = _state.value,
				)

		private val _events = Channel<SettingsEvent>()
		val events = _events.receiveAsFlow()

		fun onAction(action: SettingsAction) {
			when (action) {
				SettingsAction.SetAppVersion -> {
					updateAppVersion()
				}

				SettingsAction.OpenEditAppVersion -> {
					_state.update {
						it.copy(
							openSheetEditAppVersion = true,
							versionInput = TextFieldState(initialText = it.appVersion.toString()),
							versionInputError = null,
						)
					}
				}

				SettingsAction.CloseEditAppVersion -> {
					_state.update {
						it.copy(
							openSheetEditAppVersion = false,
							versionInputError = null,
						)
					}
				}
			}
		}

		private fun observeAppVersion() {
			viewModelScope.launch {
				getAppVersionUseCase().collect { appVersion ->
					_state.update { it.copy(appVersion = appVersion) }
				}
			}
		}

		private fun updateAppVersion() {
			if (_state.value.isLoading) return
			val version =
				_state.value.versionInput.text
					.toString()

			viewModelScope.launch {
				_state.update { it.copy(isLoading = true) }
				setAppVersionUseCase(version)
					.onSuccess {
						_state.update {
							it.copy(
								isLoading = false,
								openSheetEditAppVersion = false,
								versionInputError = null,
							)
						}
					}.onFailure { error ->
						_state.update { it.copy(isLoading = false) }
						when (error) {
							DomainError.InvalidAppVersion -> {
								_state.update { it.copy(versionInputError = error.asUiText()) }
							}

							else -> {
								_events.send(SettingsEvent.ShowSnackbar(error.asUiText()))
							}
						}
					}
			}
		}
	}
