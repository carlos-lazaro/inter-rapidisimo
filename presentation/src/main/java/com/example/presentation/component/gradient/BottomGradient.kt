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
fun BoxScope.BottomGradient(
	innerPadding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	Box(
		modifier =
			modifier
				.align(Alignment.BottomCenter)
				.fillMaxWidth()
				.height(innerPadding.calculateBottomPadding())
				.background(
					brush =
						Brush.verticalGradient(
							0.0f to Color.Transparent,
							0.1f to MaterialTheme.colorScheme.background.copy(alpha = 0.01f),
							0.2f to MaterialTheme.colorScheme.background.copy(alpha = 0.05f),
							1.0f to MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
						),
				),
	)
}
