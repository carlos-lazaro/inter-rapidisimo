package com.example.presentation.screen.table

import com.example.domain.table.model.Table

data class TableState(
	val tables: List<Table> = emptyList(),
	val isLoading: Boolean = false,
)
