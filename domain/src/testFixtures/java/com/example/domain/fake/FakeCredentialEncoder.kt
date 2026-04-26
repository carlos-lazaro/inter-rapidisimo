package com.example.domain.fake

import com.example.domain.security.encoder.CredentialEncoder

class FakeCredentialEncoder : CredentialEncoder {
	private var encoderResult: String = "encoded"

	fun setEncodeResult(result: String) {
		encoderResult = result
	}

	override fun encode(value: String): String = encoderResult
}
