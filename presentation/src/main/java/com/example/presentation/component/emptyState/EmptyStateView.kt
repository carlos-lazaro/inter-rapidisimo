package com.example.presentation.component.emptyState

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun EmptyStateView(
	title: String,
	subtitle: String,
	modifier: Modifier = Modifier,
	badgeLabel: String? = null,
	primaryActionLabel: String? = null,
	onPrimaryAction: (() -> Unit)? = null,
	secondaryActionLabel: String? = null,
	onSecondaryAction: (() -> Unit)? = null,
	illustrationContent: @Composable (() -> Unit)? = null,
) {
	Column(
		modifier = modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Box(
			modifier =
				Modifier
					.fillMaxWidth()
					.padding(horizontal = 30.dp),
		) {
			Box(
				modifier =
					Modifier
						.fillMaxWidth()
						.height(220.dp)
						.background(MaterialTheme.colorScheme.surfaceContainerLow, RoundedCornerShape(20.dp)),
				contentAlignment = Alignment.Center,
			) {
				illustrationContent?.invoke()
			}
			if (badgeLabel != null) {
				Box(
					modifier =
						Modifier
							.align(Alignment.BottomEnd)
							.offset(y = 16.dp)
							.background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(50))
							.padding(horizontal = 16.dp, vertical = 8.dp),
				) {
					Text(
						text = badgeLabel,
						style = MaterialTheme.typography.labelSmall,
						fontWeight = FontWeight.Bold,
						color = MaterialTheme.colorScheme.onTertiary,
					)
				}
			}
		}

		Spacer(Modifier.height(36.dp))

		Text(
			text = title,
			style = MaterialTheme.typography.headlineMedium,
			fontWeight = FontWeight.ExtraBold,
			textAlign = TextAlign.Center,
			color = MaterialTheme.colorScheme.onSurface,
		)
		Spacer(Modifier.height(8.dp))
		Text(
			text = subtitle,
			style = MaterialTheme.typography.bodyMedium,
			textAlign = TextAlign.Center,
			color = MaterialTheme.colorScheme.outline,
		)

		if (primaryActionLabel != null && onPrimaryAction != null) {
			Spacer(Modifier.height(24.dp))
			Button(
				onClick = onPrimaryAction,
				modifier =
					Modifier
						.fillMaxWidth()
						.padding(horizontal = 30.dp)
						.height(54.dp),
				shape = RoundedCornerShape(50.dp),
			) {
				Text(
					text = primaryActionLabel,
					style = MaterialTheme.typography.labelLarge,
					fontWeight = FontWeight.SemiBold,
				)
			}
		}

		if (secondaryActionLabel != null && onSecondaryAction != null) {
			Spacer(Modifier.height(4.dp))
			TextButton(onClick = onSecondaryAction) {
				Text(
					text = secondaryActionLabel,
					style = MaterialTheme.typography.labelLarge,
					fontWeight = FontWeight.SemiBold,
					color = MaterialTheme.colorScheme.primary,
				)
			}
		}
	}
}

@ThemePreview
@Composable
private fun EmptyStateViewPreview() {
	AppTheme {
		EmptyStateView(
			title = "Your cart is empty",
			subtitle = "Looks like you haven't added any\nfresh goodness to your basket yet.",
			badgeLabel = "🛒  0 ITEMS",
			primaryActionLabel = "Start Shopping  →",
			onPrimaryAction = {},
			secondaryActionLabel = "View Past Orders",
			onSecondaryAction = {},
			illustrationContent = {
				Text("🧺", style = MaterialTheme.typography.displayLarge)
			},
			modifier = Modifier.padding(top = 16.dp),
		)
	}
}
