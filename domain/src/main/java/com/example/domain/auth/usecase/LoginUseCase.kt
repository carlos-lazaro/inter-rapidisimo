package com.example.domain.auth.usecase

import com.example.core.util.Result
import com.example.domain.auth.model.AuthHeaders
import com.example.domain.auth.model.User
import com.example.domain.auth.model.UserForm
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.error.DomainError
import com.example.domain.error.asResultFailure
import javax.inject.Inject

class LoginUseCase
	@Inject
	constructor(
		private val authRepository: AuthRepository,
	) {
		suspend operator fun invoke(
			usuario: String,
			password: String,
		): Result<User, DomainError> {
			if (usuario.isBlank()) return DomainError.UsernameEmpty.asResultFailure()
			if (password.isBlank()) return DomainError.PasswordEmpty.asResultFailure()

			return when (
				val result =
					authRepository.login(
						userForm = UserForm(usuario = usuario, password = password),
						headers = AuthHeaders(),
					)
			) {
				is Result.Success -> result
				is Result.Failure -> DomainError.RemoteError(result.error).asResultFailure()
			}
		}
	}
