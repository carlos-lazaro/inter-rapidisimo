package com.example.domain.builder

import com.example.domain.security.model.SecurityConfig

class SecurityConfigBuilder {
	private var minimumVersion: Int = 1

	fun withMinimumVersion(minimumVersion: Int) = apply { this.minimumVersion = minimumVersion }

	fun build() =
		SecurityConfig(
			minimumVersion = minimumVersion,
		)
}

fun securityConfig(block: SecurityConfigBuilder.() -> Unit = {}) = SecurityConfigBuilder().apply(block).build()
