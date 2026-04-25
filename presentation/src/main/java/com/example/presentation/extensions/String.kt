package com.example.presentation.extensions

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toSpanishPrettyDate(): String {
	val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
	val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("es"))

	val date = LocalDate.parse(this, inputFormatter)
	return date.format(outputFormatter)
}

fun String.toFilterDateFormat(): String {
	val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
	val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

	val date = LocalDate.parse(this, inputFormatter)
	return date.format(outputFormatter)
}

fun String.normalize(): String {
	val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
	return normalized.replace("[^A-Za-z0-9 ]".toRegex(), "")
}

fun String?.matchesQuery(query: String): Boolean =
	this
		?.trim()
		?.normalize()
		?.lowercase()
		?.contains(query) == true

fun String?.fromHexToColor(): Color {
	if (this == null) return Color.Gray

	return try {
		Color(this.toColorInt())
	} catch (_: Exception) {
		Color.Gray
	}
}

fun String?.toLocalDateOrNull(pattern: String = "yyyy-MM-dd"): LocalDate? =
	try {
		this?.let {
			LocalDate.parse(it, DateTimeFormatter.ofPattern(pattern))
		}
	} catch (_: Exception) {
		null
	}

// https://api.com/api/3
fun String?.getIdFromUrl(): Int? = this?.substringAfterLast("/")?.trim()?.toIntOrNull()
