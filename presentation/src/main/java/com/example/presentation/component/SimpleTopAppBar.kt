package com.example.presentation.component

import androidx.compose.foundation.basicMarquee
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
	modifier: Modifier = Modifier,
	title: String,
	onBackSelected: () -> Unit,
) {
	TopAppBar(
		title = {
			Text(
				title,
				style = MaterialTheme.typography.titleLarge,
				fontWeight = FontWeight.Bold,
				maxLines = 1,
				modifier =
					Modifier
						.basicMarquee(),
			)
		},
		navigationIcon = {
			IconButton(onClick = { onBackSelected() }) {
				Icon(
					Icons.AutoMirrored.Filled.ArrowBack,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.onBackground,
				)
			}
		},
		colors =
			TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
				titleContentColor = MaterialTheme.colorScheme.primary,
			),
		modifier = modifier,
	)
}

@ThemePreview
@Composable
private fun SimpleTopAppBarPreview() {
	AppTheme {
		SimpleTopAppBar(title = "Title", onBackSelected = {})
	}
}
