package com.example.presentation.extensions

import androidx.compose.ui.graphics.Color

fun Color.lighten(fraction: Float): Color =
	Color(
		red = (red + (1f + red) * fraction).coerceIn(0f, 1f),
		green = (green + (1f + green) * fraction).coerceIn(0f, 1f),
		blue = (blue + (1f + blue) * fraction).coerceIn(0f, 1f),
		alpha = alpha,
	)

fun Color.lighten(): Color =
	Color(
		red = (red + 10f / 255f).coerceIn(0f, 1f),
		green = (green + 10f / 255f).coerceIn(0f, 1f),
		blue = (blue + 10f / 255f).coerceIn(0f, 1f),
		alpha = alpha,
	)

fun Color.darken(fraction: Float): Color =
	Color(
		red = (red * (1f - fraction)).coerceIn(0f, 1f),
		green = (green * (1f - fraction)).coerceIn(0f, 1f),
		blue = (blue * (1f - fraction)).coerceIn(0f, 1f),
		alpha = alpha,
	)

fun Color.darken(): Color =
	Color(
		red = (red - 10f / 255f).coerceIn(0f, 1f),
		green = (green - 10f / 255f).coerceIn(0f, 1f),
		blue = (blue - 10f / 255f).coerceIn(0f, 1f),
		alpha = alpha,
	)
