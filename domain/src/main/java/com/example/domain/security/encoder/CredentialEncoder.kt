package com.example.domain.security.encoder

interface CredentialEncoder {
	fun encode(value: String): String
}
