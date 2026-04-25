package com.example.presentation.component.gradient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BoxScope.TopGradient(
	innerPadding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	Box(
		modifier =
			modifier
				.align(Alignment.TopCenter)
				.fillMaxWidth()
				.height(innerPadding.calculateTopPadding())
				.background(
					brush =
						Brush.verticalGradient(
							0.0f to MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
							0.8f to MaterialTheme.colorScheme.background.copy(alpha = 0.05f),
							0.9f to MaterialTheme.colorScheme.background.copy(alpha = 0.01f),
							1.0f to Color.Transparent,
						),
				),
	)
}
