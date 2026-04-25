package com.example.data.localidad.local

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.data.localidad.local.dao.LocalidadDao
import com.example.data.localidad.local.entity.LocalidadEntity
import com.example.data.util.safeLocalCall
import javax.inject.Inject

class LocalidadLocalDataSource
	@Inject
	constructor(
		private val dao: LocalidadDao,
	) {
		fun getAllLocalidades() = dao.getAllLocalidades()

		suspend fun replaceLocalidades(localidades: List<LocalidadEntity>): EmptyResult<DataError.Local> = safeLocalCall { dao.replaceAll(localidades) }

		suspend fun clearLocalidades(): EmptyResult<DataError.Local> = safeLocalCall { dao.deleteAll() }
	}
