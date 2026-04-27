package com.example.domain.table.usecase

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.domain.table.repository.TableRepository
import javax.inject.Inject

class SyncTablesUseCase
	@Inject
	constructor(
		private val repository: TableRepository,
	) {
		suspend operator fun invoke(): EmptyResult<DataError> = repository.syncTables(usuario = "usuario")
	}
