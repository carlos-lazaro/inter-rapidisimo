package com.example.data.auth.encoder

import android.util.Base64
import com.example.domain.security.encoder.CredentialEncoder
import javax.inject.Inject

class Base64CredentialEncoder
	@Inject
	constructor() : CredentialEncoder {
		override fun encode(value: String): String =
			Base64.encodeToString(
				value.toByteArray(Charsets.UTF_8),
				Base64.NO_WRAP,
			)
	}
