package com.example.presentation.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.extensions.bottomBorder
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun TopContainer(
	modifier: Modifier = Modifier,
	content: @Composable ColumnScope.() -> Unit,
) {
	Column(
		modifier =
			modifier
				.fillMaxWidth()
				.statusBarsPadding()
				.background(MaterialTheme.colorScheme.background),
	) {
		content()
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		Box(
			modifier =
				Modifier
					.fillMaxWidth()
					.padding(bottom = 16.dp),
		) {
			TopContainer {
				Text(
					"Hello",
					color = MaterialTheme.colorScheme.onBackground,
					modifier =
						Modifier
							.fillMaxWidth()
							.padding(bottom = 16.dp),
				)
				Text("Content")
			}
		}
	}
}
