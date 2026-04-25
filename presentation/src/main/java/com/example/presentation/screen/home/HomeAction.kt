package com.example.presentation.screen.home

sealed interface HomeAction {
	data object Logout : HomeAction
}
