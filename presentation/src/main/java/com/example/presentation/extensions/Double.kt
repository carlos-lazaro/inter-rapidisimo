package com.example.presentation.extensions

import java.text.NumberFormat
import java.util.Currency

fun Double.toCurrencyString(): String =
	NumberFormat
		.getCurrencyInstance()
		.apply {
			currency = Currency.getInstance("USD")
		}.format(this)
