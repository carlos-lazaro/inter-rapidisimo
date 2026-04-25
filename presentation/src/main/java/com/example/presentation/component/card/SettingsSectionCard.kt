package com.example.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun SettingsSectionCard(
	icon: ImageVector,
	title: String,
	modifier: Modifier = Modifier,
	content: @Composable ColumnScope.(padding: Dp) -> Unit,
) {
	val padding = 16.dp

	Card(
		elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
		shape = MaterialTheme.shapes.large,
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
		modifier = modifier.fillMaxWidth(),
	) {
		Column(
			modifier = Modifier,
		) {
			Spacer(modifier = Modifier.size(padding))

			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				modifier =
					Modifier
						.fillMaxWidth()
						.padding(horizontal = padding),
			) {
				Icon(
					imageVector = icon,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.primary,
					modifier = Modifier,
				)
				Text(
					text = title,
					style = MaterialTheme.typography.titleMedium,
					color = MaterialTheme.colorScheme.onSurface,
					fontWeight = FontWeight.Bold,
				)
			}

			Spacer(modifier = Modifier.size(padding / 2))
			HorizontalDivider()
			Spacer(modifier = Modifier.size(padding / 2))

			content(padding)

			Spacer(modifier = Modifier.size(padding))
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		Column(
			modifier = Modifier.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			SettingsSectionCard(
				icon = Icons.Default.Info,
				title = "Filters and Display",
			) {
				Text("Content goes here", style = MaterialTheme.typography.bodyMedium)
			}
			SettingsSectionCard(
				icon = Icons.Default.DarkMode,
				title = "Appearance",
			) {
				Text("Content goes here", style = MaterialTheme.typography.bodyMedium)
			}
		}
	}
}
