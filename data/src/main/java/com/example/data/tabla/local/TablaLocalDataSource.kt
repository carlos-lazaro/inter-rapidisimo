package com.example.data.tabla.local

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.data.tabla.local.dao.TablaDao
import com.example.data.tabla.local.entity.TablaEntity
import com.example.data.util.safeLocalCall
import javax.inject.Inject

class TablaLocalDataSource
	@Inject
	constructor(
		private val dao: TablaDao,
	) {
		fun getAllTablas() = dao.getAllTablas()

		suspend fun replaceTablas(tablas: List<TablaEntity>): EmptyResult<DataError.Local> = safeLocalCall { dao.replaceAll(tablas) }

		suspend fun clearTablas(): EmptyResult<DataError.Local> = safeLocalCall { dao.deleteAll() }
	}
