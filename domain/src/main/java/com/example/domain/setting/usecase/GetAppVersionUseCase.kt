package com.example.domain.setting.usecase

import com.example.domain.setting.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAppVersionUseCase
	@Inject
	constructor(
		private val settingsRepository: SettingsRepository,
	) {
		operator fun invoke(): Flow<Int> = settingsRepository.appVersion.map { version -> if (version < 1) 1 else version }
	}
