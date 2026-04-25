package com.example.presentation.component.chip

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun CategoryChip(
	modifier: Modifier = Modifier,
	name: String? = null,
	onClick: (() -> Unit)? = null,
) {
	name?.let {
		BasicChip(
			it,
			color = MaterialTheme.colorScheme.secondary,
			showBorder = false,
			modifier = modifier,
			onClick = onClick,
		)
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		CategoryChip(name = "Fruit")
	}
}
