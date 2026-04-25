package com.example.presentation.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

enum class AppButtonStyle {
	PRIMARY,
	DESTRUCTIVE_PRIMARY,
	SECONDARY,
	DESTRUCTIVE_SECONDARY,
	TEXT,
}

@Composable
fun AppButton(
	modifier: Modifier = Modifier,
	text: String,
	onClick: () -> Unit,
	style: AppButtonStyle = AppButtonStyle.PRIMARY,
	enabled: Boolean = true,
	isLoading: Boolean = false,
	leadingIcon: @Composable (RowScope.() -> Unit)? = null,
	trailingIcon: @Composable (RowScope.() -> Unit)? = null,
) {
	val disabledContainerColor = MaterialTheme.colorScheme.outline
	val disabledContentColor = MaterialTheme.colorScheme.outlineVariant
	val contentColor = MaterialTheme.colorScheme.outlineVariant

	val colors =
		when (style) {
			AppButtonStyle.PRIMARY -> {
				ButtonDefaults.buttonColors(
					containerColor = MaterialTheme.colorScheme.primary,
					contentColor = MaterialTheme.colorScheme.onPrimary,
					disabledContainerColor = disabledContainerColor,
					disabledContentColor = disabledContentColor,
				)
			}

			AppButtonStyle.DESTRUCTIVE_PRIMARY -> {
				ButtonDefaults.buttonColors(
					containerColor = MaterialTheme.colorScheme.error,
					contentColor = MaterialTheme.colorScheme.onError,
					disabledContainerColor = disabledContainerColor,
					disabledContentColor = disabledContentColor,
				)
			}

			AppButtonStyle.SECONDARY -> {
				ButtonDefaults.buttonColors(
					containerColor = Color.Transparent,
					contentColor = contentColor,
					disabledContainerColor = Color.Transparent,
					disabledContentColor = disabledContentColor,
				)
			}

			AppButtonStyle.DESTRUCTIVE_SECONDARY -> {
				ButtonDefaults.buttonColors(
					containerColor = Color.Transparent,
					contentColor = MaterialTheme.colorScheme.error,
					disabledContainerColor = Color.Transparent,
					disabledContentColor = disabledContentColor,
				)
			}

			AppButtonStyle.TEXT -> {
				ButtonDefaults.buttonColors(
					containerColor = Color.Transparent,
					contentColor = MaterialTheme.colorScheme.tertiary,
					disabledContainerColor = Color.Transparent,
					disabledContentColor = disabledContentColor,
				)
			}
		}

	val defaultBorderStroke =
		BorderStroke(
			width = 1.dp,
			color = disabledContainerColor,
		)
	val border =
		when (style) {
			AppButtonStyle.PRIMARY if !enabled -> {
				defaultBorderStroke
			}

			AppButtonStyle.SECONDARY -> {
				defaultBorderStroke
			}

			AppButtonStyle.DESTRUCTIVE_PRIMARY if !enabled -> {
				defaultBorderStroke
			}

			AppButtonStyle.DESTRUCTIVE_SECONDARY -> {
				val borderColor =
					if (enabled) {
						disabledContainerColor
					} else {
						disabledContentColor
					}
				BorderStroke(
					width = 1.dp,
					color = borderColor,
				)
			}

			else -> {
				null
			}
		}

	Button(
		modifier = modifier,
		onClick = {
			if (!isLoading) {
				onClick()
			}
		},
		enabled = enabled,
		shape = RoundedCornerShape(8.dp),
		colors = colors,
		border = border,
	) {
		Box(
			contentAlignment = Alignment.Center,
			modifier =
				Modifier
					.padding(6.dp),
		) {
			CircularProgressIndicator(
				strokeWidth = 1.5.dp,
				color = Color.Black,
				modifier =
					Modifier
						.size(15.dp)
						.alpha(
							alpha = if (isLoading) 1f else 0f,
						),
			)
			Row(
				horizontalArrangement =
					Arrangement.spacedBy(
						8.dp,
						Alignment.CenterHorizontally,
					),
				verticalAlignment = Alignment.CenterVertically,
				modifier =
					Modifier.alpha(
						alpha = if (isLoading) 0f else 1f,
					),
			) {
				leadingIcon?.let {
					it()
				}
				Text(
					text = text,
					style = MaterialTheme.typography.titleSmall,
				)
				trailingIcon?.let {
					it()
				}
			}
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			AppButtonStyle.entries.forEach { style ->
				AppButton(text = style.name, onClick = {}, style = style)
				AppButton(text = style.name, onClick = {}, style = style, isLoading = true)
			}
		}
	}
}
