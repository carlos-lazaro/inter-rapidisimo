package com.example.presentation.screen.home

import com.example.domain.auth.model.User

data class HomeState(
	val user: User? = null,
	val isLoggingOut: Boolean = false,
)
