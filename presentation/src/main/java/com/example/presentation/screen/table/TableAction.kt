package com.example.presentation.screen.table

sealed interface TableAction {
	data object Refresh : TableAction
}
