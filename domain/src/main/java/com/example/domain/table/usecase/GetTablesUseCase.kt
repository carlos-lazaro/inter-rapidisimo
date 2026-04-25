package com.example.domain.table.usecase

import com.example.domain.table.model.Table
import com.example.domain.table.repository.TableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTablesUseCase
	@Inject
	constructor(
		private val repository: TableRepository,
	) {
		operator fun invoke(): Flow<List<Table>> = repository.getTables()
	}
