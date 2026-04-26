package com.example.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.Result
import com.example.core.util.onFailure
import com.example.core.util.onSuccess
import com.example.domain.auth.usecase.ClearLocalCacheUseCase
import com.example.domain.auth.usecase.GetAuthUserUseCase
import com.example.domain.auth.usecase.LogoutUseCase
import com.example.domain.security.usecase.CheckMinimumVersionUseCase
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
class HomeViewModel
	@Inject
	constructor(
		private val getAuthUserUseCase: GetAuthUserUseCase,
		private val logoutUseCase: LogoutUseCase,
		private val clearLocalCacheUseCase: ClearLocalCacheUseCase,
		private val checkMinimumVersionUseCase: CheckMinimumVersionUseCase,
	) : ViewModel() {
		private var hasLoadedInitialData = false

		private val _state = MutableStateFlow(HomeState())
		val state =
			_state
				.onStart {
					if (!hasLoadedInitialData) {
						loadAuthUser()
						checkMinimumVersion()
						hasLoadedInitialData = true
					}
				}.stateIn(
					scope = viewModelScope,
					started = SharingStarted.WhileSubscribed(5_000L),
					initialValue = _state.value,
				)

		private val _events = Channel<HomeEvent>()
		val events = _events.receiveAsFlow()

		fun onAction(action: HomeAction) {
			when (action) {
				HomeAction.Logout -> logout()
			}
		}

		private fun loadAuthUser() {
			viewModelScope.launch {
				getAuthUserUseCase().collect { user ->
					_state.update { it.copy(user = user) }
				}
			}
		}

		private fun checkMinimumVersion() {
			viewModelScope.launch {
				checkMinimumVersionUseCase()
					.onSuccess {
						_events.send(HomeEvent.ShowSnackbar(UiText.StringResource(R.string.app_version_updated)))
					}.onFailure { error ->
						_events.send(HomeEvent.ShowSnackbar(error.asUiText()))
					}
			}
		}

		private fun logout() {
			viewModelScope.launch {
				_state.update { it.copy(isLoggingOut = true) }
				when (val result = logoutUseCase()) {
					is Result.Success -> {
						clearLocalCacheUseCase()
					}

					is Result.Failure -> {
						_state.update { it.copy(isLoggingOut = false) }
						_events.send(HomeEvent.ShowSnackbar(result.error.asUiText()))
					}
				}
			}
		}
	}
