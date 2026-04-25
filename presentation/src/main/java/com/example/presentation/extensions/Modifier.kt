package com.example.presentation.extensions

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

fun Modifier.enabledAlpha(enabled: Boolean): Modifier =
	if (enabled) {
		this
	} else {
		this.alpha(0.5f)
	}

fun Modifier.coloredShadow(
	color: Color,
	borderRadius: Dp = 0.dp,
	blurRadius: Dp = 0.dp,
	offsetX: Dp = 0.dp,
	offsetY: Dp = 0.dp,
	spread: Float = 0f,
) = this.drawBehind {
	drawIntoCanvas {
		val paint = Paint()
		val frameworkPaint = paint.asFrameworkPaint()
		val spreadPixel = spread.dp.toPx()
		val leftPixel = (0f - spreadPixel) + offsetX.toPx()
		val topPixel = (0f - spreadPixel) + offsetY.toPx()
		val rightPixel = size.width + spreadPixel
		val bottomPixel = size.height + spreadPixel

		if (blurRadius != 0.dp) {
			frameworkPaint.maskFilter = BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL)
		}

		frameworkPaint.color = color.copy(alpha = 0f).toArgb()
		frameworkPaint.setShadowLayer(blurRadius.toPx(), offsetX.toPx(), offsetY.toPx(), color.toArgb())

		it.drawRoundRect(
			left = leftPixel,
			top = topPixel,
			right = rightPixel,
			bottom = bottomPixel,
			radiusX = borderRadius.toPx(),
			radiusY = borderRadius.toPx(),
			paint = paint,
		)
	}
}

fun Modifier.neumorphicRaisedFractionShadow(
	color: Color,
	shape: Shape,
	upperOffset: Dp = (-10).dp,
	lowerOffset: Dp = 10.dp,
	radius: Dp = 15.dp,
	spread: Dp = 0.dp,
): Modifier =
	this
		.dropShadow(
			shape = shape,
			shadow =
				Shadow(
					radius = radius,
					color = color.lighten(0.05f),
					spread = spread,
					offset = DpOffset(upperOffset, upperOffset),
				),
		).dropShadow(
			shape = shape,
			shadow =
				Shadow(
					radius = radius,
					color = color.darken(0.05f),
					spread = spread,
					offset = DpOffset(lowerOffset, lowerOffset),
				),
		).background(color, shape)
		.clip(shape)

fun Modifier.topBorder(color: Color): Modifier =
	this.drawWithContent {
		drawContent()
		val strokeWidth = 1.dp.toPx()
		drawLine(
			color = color,
			start = Offset(0f, strokeWidth / 2),
			end = Offset(size.width, strokeWidth / 2),
			strokeWidth = strokeWidth,
		)
	}

fun Modifier.bottomBorder(color: Color): Modifier =
	this.drawWithContent {
		drawContent()
		val strokeWidth = 1.dp.toPx()
		drawLine(
			color = color,
			start = Offset(0f, size.height - strokeWidth / 2),
			end = Offset(size.width, size.height - strokeWidth / 2),
			strokeWidth = strokeWidth,
		)
	}

fun Modifier.neumorphicRaisedShadow(
	color: Color,
	shape: Shape,
	upperOffset: Dp = (-10).dp,
	lowerOffset: Dp = 10.dp,
	radius: Dp = 15.dp,
	spread: Dp = 0.dp,
): Modifier =
	this
		.dropShadow(
			shape = shape,
			shadow =
				Shadow(
					radius = radius,
					color = color.lighten(),
					spread = spread,
					offset = DpOffset(upperOffset, upperOffset),
				),
		).dropShadow(
			shape = shape,
			shadow =
				Shadow(
					radius = radius,
					color = color.darken(),
					spread = spread,
					offset = DpOffset(lowerOffset, lowerOffset),
				),
		).background(color, shape)
		.clip(shape)
