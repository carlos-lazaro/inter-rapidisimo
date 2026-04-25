package com.example.presentation.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toStringWithFormat(pattern: String = "yyyy-MM-dd"): String = this.format(DateTimeFormatter.ofPattern(pattern))
