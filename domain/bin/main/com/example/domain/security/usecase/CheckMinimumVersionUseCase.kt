package com.example.domain.security.usecase

import com.example.core.util.EmptyResult
import com.example.core.util.Result
import com.example.domain.error.DomainError
import com.example.domain.error.asResultFailure
import com.example.domain.security.repository.SecurityRepository
import com.example.domain.setting.repository.SettingsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CheckMinimumVersionUseCase
	@Inject
	constructor(
		private val securityRepository: SecurityRepository,
		private val settingsRepository: SettingsRepository,
	) {
		suspend operator fun invoke(): EmptyResult<DomainError> {
			val currentVersion = settingsRepository.appVersion.first()

			return when (val result = securityRepository.getConfig()) {
				is Result.Success -> {
					if (currentVersion < result.data.minimumVersion) {
						DomainError.AppVersionOutdated.asResultFailure()
					} else {
						Result.Success(Unit)
					}
				}

				is Result.Failure -> {
					DomainError.RemoteError(result.error).asResultFailure()
				}
			}
		}
	}
