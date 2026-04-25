package com.example.domain.setting.usecase

import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.error.DomainError
import com.example.domain.error.asResultFailure
import com.example.domain.setting.repository.SettingsRepository
import javax.inject.Inject

class SetAppVersionUseCase
	@Inject
	constructor(
		private val settingsRepository: SettingsRepository,
	) {
		suspend operator fun invoke(rawValue: String): EmptyResult<DomainError> {
			val version = rawValue.trim().toIntOrNull()
			if (version == null || version < 1) return DomainError.InvalidAppVersion.asResultFailure()
			return when (val result = settingsRepository.setAppVersion(version)) {
				is Result.Success -> result
				is Result.Failure -> DomainError.LocalError(result.error).asResultFailure()
			}
		}
	}
