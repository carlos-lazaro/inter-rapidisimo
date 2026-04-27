package com.example.domain.auth.usecase

import com.example.core.util.Result
import com.example.domain.auth.model.AuthHeaders
import com.example.domain.auth.model.User
import com.example.domain.auth.model.UserForm
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.error.DomainError
import com.example.domain.error.asResultFailure
import com.example.domain.error.toDomainError
import com.example.domain.security.encoder.CredentialEncoder
import javax.inject.Inject

class LoginUseCase
	@Inject
	constructor(
		private val authRepository: AuthRepository,
		private val credentialEncoder: CredentialEncoder,
	) {
		suspend operator fun invoke(
			username: String,
			password: String,
		): Result<User, DomainError> {
			if (username.trim().isBlank()) return DomainError.UsernameEmpty.asResultFailure()
			if (password.trim().isBlank()) return DomainError.PasswordEmpty.asResultFailure()

			return when (
				val result =
					authRepository.login(
						userForm =
							UserForm(
								usuario = credentialEncoder.encode(username),
								password = credentialEncoder.encode(password),
							),
						headers =
							AuthHeaders(
								usuario = username,
								idUsuario = username,
							),
					)
			) {
				is Result.Success -> result
				is Result.Failure -> result.error.toDomainError().asResultFailure()
			}
		}
	}
