package com.example.presentation.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun BasicSolidChip(
	text: String,
	modifier: Modifier = Modifier,
	color: Color = MaterialTheme.colorScheme.primary,
	onColor: Color = MaterialTheme.colorScheme.onPrimary,
	startIcon: Painter? = null,
	startSlot: @Composable (RowScope.(size: Dp, color: Color) -> Unit)? = null,
	endIcon: Painter? = null,
	endSlot: @Composable (RowScope.(size: Dp, color: Color) -> Unit)? = null,
	onClick: (() -> Unit)? = null,
) {
	BasicSolidChip(
		annotatedText = buildAnnotatedString { append(text.uppercase()) },
		modifier = modifier,
		color = color,
		onColor = onColor,
		startIcon = startIcon,
		startSlot = startSlot,
		endIcon = endIcon,
		endSlot = endSlot,
		onClick = onClick,
	)
}

@Composable
fun BasicSolidChip(
	annotatedText: AnnotatedString,
	modifier: Modifier = Modifier,
	color: Color = MaterialTheme.colorScheme.primary,
	onColor: Color = MaterialTheme.colorScheme.onPrimary,
	startIcon: Painter? = null,
	startSlot: @Composable (RowScope.(size: Dp, color: Color) -> Unit)? = null,
	endIcon: Painter? = null,
	endSlot: @Composable (RowScope.(size: Dp, color: Color) -> Unit)? = null,
	onClick: (() -> Unit)? = null,
) {
	val shape = RoundedCornerShape(50)
	val style = MaterialTheme.typography.labelMedium

	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		modifier =
			modifier
				.background(
					color = color,
					shape = shape,
				).clip(shape = shape)
				.then(
					if (onClick != null) {
						Modifier.clickable { onClick() }
					} else {
						Modifier
					},
				).padding(
					vertical = 4.dp,
					horizontal = 12.dp,
				),
	) {
		when {
			startIcon != null -> {
				Icon(
					startIcon,
					tint = onColor,
					contentDescription = null,
					modifier =
						Modifier
							.size(style.lineHeight.value.dp),
				)
			}

			startSlot != null -> {
				startSlot(style.lineHeight.value.dp, onColor)
			}
		}

		Text(
			annotatedText,
			color = onColor,
			style = style,
			fontWeight = FontWeight.Bold,
		)

		when {
			endIcon != null -> {
				Icon(
					endIcon,
					tint = onColor,
					contentDescription = null,
					modifier =
						Modifier
							.size(style.lineHeight.value.dp),
				)
			}

			endSlot != null -> {
				endSlot(style.lineHeight.value.dp, onColor)
			}
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		val slot: @Composable (Dp, Color) -> Unit = { size, color ->
			Icon(
				painter = painterResource(R.drawable.rounded_question_mark_24),
				contentDescription = null,
				tint = color,
				modifier =
					Modifier
						.size(size * 2),
			)
		}

		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			BasicSolidChip(
				text = "name",
			)
			BasicSolidChip(
				text = "name",
				startIcon = painterResource(R.drawable.rounded_question_mark_24),
			)
			BasicSolidChip(
				text = "name",
				startSlot = { size, color ->
					slot(size, color)
				},
			)
			BasicSolidChip(
				text = "name",
				endIcon = painterResource(R.drawable.rounded_question_mark_24),
			)
			BasicSolidChip(
				text = "name",
				endSlot = { size, color ->
					slot(size, color)
				},
			)
			BasicSolidChip(
				text = "name",
				startIcon = painterResource(R.drawable.rounded_question_mark_24),
				endIcon = painterResource(R.drawable.rounded_question_mark_24),
			)
			BasicSolidChip(
				text = "name",
				startSlot = { size, color ->
					slot(size, color)
				},
				endSlot = { size, color ->
					slot(size, color)
				},
			)
		}
	}
}
