package com.example.domain.auth.usecase

import com.example.domain.auth.model.User
import com.example.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthUserUseCase
	@Inject
	constructor(
		private val authRepository: AuthRepository,
	) {
		operator fun invoke(): Flow<User?> = authRepository.getAuthUser()
	}
