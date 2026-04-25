package com.example.presentation.theme.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
	name = "Light Mode",
	showBackground = true,
	uiMode = Configuration.UI_MODE_NIGHT_NO,
)
annotation class LightPreview

@Preview(
	name = "Dark Mode",
	showBackground = true,
	uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class DarkPreview

@Preview(
	name = "Light Mode",
	showBackground = true,
	uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
	name = "Dark Mode",
	showBackground = true,
	backgroundColor = 0xFF0C0C0C,
	uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class ThemePreview
