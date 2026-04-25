package com.example.presentation.screen.table

import com.example.presentation.util.UiText

sealed interface TableEvent {
	data class ShowSnackbar(
		val message: UiText,
	) : TableEvent
}
