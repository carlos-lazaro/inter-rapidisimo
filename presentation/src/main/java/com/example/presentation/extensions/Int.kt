package com.example.presentation.extensions

import java.text.DecimalFormat
import java.util.Locale

fun Int.toPrettyMoney(): String {
	if (this >= 10_000_000) {
		val millions = this / 1_000_000.0
		val formatted = String.format(Locale.US, "%.1f", millions)
		return formatted.replace(".", ",") + "m"
	}

	val formatter = DecimalFormat("#,##0")
	val formatted = formatter.format(this)

	return formatted
		.replace(",", ".")
}

fun Int?.orEmptyIfZero(): String = if (this == null || this == 0) "" else this.toString()
