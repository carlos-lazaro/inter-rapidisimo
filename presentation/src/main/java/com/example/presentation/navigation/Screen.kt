package com.example.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {
	@Serializable
	data object Login : Screen

	@Serializable
	data object Home : Screen

	@Serializable
	data object Tables : Screen

	@Serializable
	data object Locations : Screen

	@Serializable
	data object Setting : Screen
}
