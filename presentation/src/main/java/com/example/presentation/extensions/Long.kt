package com.example.presentation.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toDateTimeString(): String {
	val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
	return dateFormat.format(Date(this))
}

fun Long.formatToServerDateDefaultsWithUTC(): String {
	val date = Date(this)
	val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

	sdf.timeZone = TimeZone.getTimeZone("UTC")

	return sdf.format(date)
}
