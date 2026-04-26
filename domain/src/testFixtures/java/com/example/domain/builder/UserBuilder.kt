package com.example.domain.builder

import com.example.domain.auth.model.User

class UserBuilder {
	private var username: String = "testuser"
	private var identification: String? = "123456789"
	private var name: String? = "Test User"

	fun withUsername(username: String) = apply { this.username = username }

	fun withIdentification(identification: String?) = apply { this.identification = identification }

	fun withName(name: String?) = apply { this.name = name }

	fun build() =
		User(
			username = username,
			identification = identification,
			name = name,
		)
}

fun user(block: UserBuilder.() -> Unit = {}) = UserBuilder().apply(block).build()
