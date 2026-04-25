package com.example.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun SettingSwitchRow(
	title: String,
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
	description: String? = null,
) {
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
		modifier =
			modifier
				.fillMaxWidth(),
	) {
		Column(modifier = Modifier.weight(1f)) {
			Text(
				text = title,
				style = MaterialTheme.typography.bodyMedium,
				fontWeight = FontWeight.Medium,
				color = MaterialTheme.colorScheme.onSurface,
			)
			if (description != null) {
				Text(
					text = description,
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.outline,
				)
			}
		}
		Switch(checked = checked, onCheckedChange = onCheckedChange)
	}
}

@ThemePreview
@Composable
private fun SettingSwitchRowPreview() {
	var checked by remember { mutableStateOf(true) }
	AppTheme {
		Column {
			SettingSwitchRow(
				title = "Only products in Stock",
				description = "Hide items that are currently unavailable",
				checked = checked,
				onCheckedChange = { checked = it },
			)
			SettingSwitchRow(
				title = "Push Notifications",
				checked = false,
				onCheckedChange = {},
			)
		}
	}
}
