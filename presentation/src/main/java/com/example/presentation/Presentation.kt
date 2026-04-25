package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.navigation.NavigationGraph
import com.example.presentation.navigation.Screen
import com.example.presentation.theme.AppTheme

@Composable
fun Presentation(viewModel: PresentationViewModel = hiltViewModel()) {
	val session by viewModel.session.collectAsStateWithLifecycle()

	AppTheme {
		when (session) {
			SessionState.Loading -> {}

			SessionState.LoggedIn -> {
				NavigationGraph(startScreen = Screen.Home)
			}

			SessionState.LoggedOut -> {
				NavigationGraph(startScreen = Screen.Login)
			}
		}
	}
}
