package com.example.domain.auth.usecase

import com.example.core.util.DataError
import com.example.core.util.EmptyResult
import com.example.domain.auth.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase
	@Inject
	constructor(
		private val authRepository: AuthRepository,
	) {
		suspend operator fun invoke(): EmptyResult<DataError.Local> = authRepository.logout()
	}
