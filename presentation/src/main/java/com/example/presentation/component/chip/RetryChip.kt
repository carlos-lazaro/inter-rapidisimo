package com.example.presentation.component.chip

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.presentation.R
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview

@Composable
fun RetryChip(
	modifier: Modifier = Modifier,
	onClick: (() -> Unit)? = null,
) {
	BasicChip(
		stringResource(R.string.retry),
		startIcon = painterResource(R.drawable.rounded_error_24),
		modifier = modifier,
		onClick = onClick,
	)
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		RetryChip()
	}
}
