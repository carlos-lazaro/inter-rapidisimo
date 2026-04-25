package com.example.presentation.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun BasicChip(
	text: String,
	modifier: Modifier = Modifier,
	color: Color = MaterialTheme.colorScheme.primary,
	showBorder: Boolean = true,
	startIcon: Painter? = null,
	startSlot: @Composable (RowScope.(size: Dp, color: Color) -> Unit)? = null,
	endIcon: Painter? = null,
	endSlot: @Composable (RowScope.(size: Dp, color: Color) -> Unit)? = null,
	onClick: (() -> Unit)? = null,
) {
	BasicChip(
		annotatedText = buildAnnotatedString { append(text.uppercase()) },
		modifier = modifier,
		color = color,
		showBorder = showBorder,
		startIcon = startIcon,
		startSlot = startSlot,
		endIcon = endIcon,
		endSlot = endSlot,
		onClick = onClick,
	)
}

@Composable
fun BasicChip(
	annotatedText: AnnotatedString,
	modifier: Modifier = Modifier,
	color: Color = MaterialTheme.colorScheme.primary,
	showBorder: Boolean = true,
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
				.then(
					if (showBorder) {
						Modifier.border(
							width = 1.dp,
							color = color,
							shape = shape,
						)
					} else {
						Modifier
					},
				).background(
					color = color.copy(alpha = 0.15f),
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
					tint = color,
					contentDescription = null,
					modifier =
						Modifier
							.size(style.lineHeight.value.dp),
				)
			}

			startSlot != null -> {
				startSlot(style.lineHeight.value.dp, color)
			}
		}

		Text(
			annotatedText,
			color = color,
			style = style,
			fontWeight = FontWeight.Bold,
		)

		when {
			endIcon != null -> {
				Icon(
					endIcon,
					tint = color,
					contentDescription = null,
					modifier =
						Modifier
							.size(style.lineHeight.value.dp),
				)
			}

			endSlot != null -> {
				endSlot(style.lineHeight.value.dp, color)
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
			BasicChip(
				text = "name",
				showBorder = false,
			)
			BasicChip(
				text = "name",
			)
			BasicChip(
				text = "name",
				startIcon = painterResource(R.drawable.rounded_question_mark_24),
			)
			BasicChip(
				text = "name",
				startSlot = { size, color ->
					slot(size, color)
				},
			)
			BasicChip(
				text = "name",
				endIcon = painterResource(R.drawable.rounded_question_mark_24),
			)
			BasicChip(
				text = "name",
				endSlot = { size, color ->
					slot(size, color)
				},
			)
			BasicChip(
				text = "name",
				startIcon = painterResource(R.drawable.rounded_question_mark_24),
				endIcon = painterResource(R.drawable.rounded_question_mark_24),
			)
			BasicChip(
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
