package com.example.presentation.component.emptyState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.component.button.AppButton
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun EmptyStateView(
	modifier: Modifier = Modifier,
	isLoading: Boolean = false,
	retry: (() -> Unit)? = null,
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier =
			modifier
				.padding(32.dp),
	) {
		Box(
			contentAlignment = Alignment.Center,
			modifier =
				Modifier
					.size(200.dp)
					.background(
						MaterialTheme.colorScheme.outline.copy(alpha = 0.1f),
						CircleShape,
					),
		) {
			Icon(
				imageVector = Icons.Outlined.FolderOpen,
				contentDescription = stringResource(R.string.icon),
				modifier = Modifier.size(100.dp),
				tint = MaterialTheme.colorScheme.secondary,
			)
		}

		Spacer(modifier = Modifier.height(48.dp))

		Text(
			text = stringResource(R.string.no_results_found),
			style = MaterialTheme.typography.headlineMedium,
			color = MaterialTheme.colorScheme.onBackground,
			fontWeight = FontWeight.Bold,
		)

		Spacer(modifier = Modifier.height(16.dp))

		retry?.let {
			AppButton(
				text = stringResource(R.string.retry),
				onClick = it,
				isLoading = isLoading,
				modifier = Modifier.fillMaxWidth(),
			)
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		EmptyStateView(
			retry = {},
		)
	}
}
