package com.example.presentation.component.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun AppPasswordTextField(
	state: TextFieldState,
	isPasswordVisible: Boolean,
	onToggleVisibilityClick: () -> Unit,
	modifier: Modifier = Modifier,
	placeholder: String? = null,
	title: String? = null,
	supportingText: String? = null,
	isError: Boolean = false,
	enabled: Boolean = true,
	onFocusChanged: (Boolean) -> Unit = {},
) {
	AppTextFieldLayout(
		title = title,
		isError = isError,
		supportingText = supportingText,
		enabled = enabled,
		onFocusChanged = onFocusChanged,
		modifier = modifier,
	) { styleModifier, interactionSource ->
		BasicSecureTextField(
			state = state,
			modifier = styleModifier,
			enabled = enabled,
			textObfuscationMode =
				if (isPasswordVisible) {
					TextObfuscationMode.Visible
				} else {
					TextObfuscationMode.Hidden
				},
			keyboardOptions =
				KeyboardOptions(
					keyboardType = KeyboardType.Password,
				),
			textStyle =
				MaterialTheme.typography.bodyMedium.copy(
					color =
						if (enabled) {
							MaterialTheme.colorScheme.onSurface
						} else {
							MaterialTheme.colorScheme.outline
						},
				),
			interactionSource = interactionSource,
			cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
			decorator = { innerBox ->
				Row(
					modifier =
						Modifier
							.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically,
				) {
					Box(
						modifier =
							Modifier
								.weight(1f),
						contentAlignment = Alignment.CenterStart,
					) {
						if (state.text.isEmpty() && placeholder != null) {
							Text(
								text = placeholder,
								color = MaterialTheme.colorScheme.outline,
								style = MaterialTheme.typography.bodyMedium,
							)
						}
						innerBox()
					}

					Icon(
						painter =
							if (isPasswordVisible) {
								painterResource(R.drawable.rounded_visibility_off_24)
							} else {
								painterResource(R.drawable.rounded_visibility_24)
							},
						contentDescription =
							if (isPasswordVisible) {
								stringResource(R.string.hide_password)
							} else {
								stringResource(R.string.show_password)
							},
						tint = MaterialTheme.colorScheme.outline,
						modifier =
							Modifier
								.size(24.dp)
								.clickable(
									interactionSource = remember { MutableInteractionSource() },
									indication =
										ripple(
											bounded = false,
											radius = 24.dp,
										),
									onClick = onToggleVisibilityClick,
								),
					)
				}
			},
		)
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		AppPasswordTextField(
			state = rememberTextFieldState("password123"),
			isPasswordVisible = true,
			onToggleVisibilityClick = {},
			placeholder = "Password",
			title = "Password",
			supportingText = "Doesn't contain an uppercase character",
			isError = true,
			modifier =
				Modifier
					.width(300.dp),
		)
	}
}
