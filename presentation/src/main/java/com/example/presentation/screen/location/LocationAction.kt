package com.example.presentation.screen.location

sealed interface LocationAction {
	data object Refresh : LocationAction
}
