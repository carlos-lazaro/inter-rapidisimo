package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.usecase.GetAuthUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PresentationViewModel
	@Inject
	constructor(
		getAuthUserUseCase: GetAuthUserUseCase,
	) : ViewModel() {
		val session =
			getAuthUserUseCase()
				.map { user -> if (user != null) SessionState.LoggedIn else SessionState.LoggedOut }
				.stateIn(
					scope = viewModelScope,
					started = SharingStarted.WhileSubscribed(5_000L),
					initialValue = SessionState.Loading,
				)
	}
