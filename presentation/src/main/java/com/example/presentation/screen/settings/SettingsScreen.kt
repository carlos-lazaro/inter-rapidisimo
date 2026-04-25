package com.example.presentation.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.component.SimpleTopAppBar
import com.example.presentation.component.button.AppButton
import com.example.presentation.component.button.AppButtonStyle
import com.example.presentation.component.card.SettingsSectionCard
import com.example.presentation.component.textfields.AppTextField
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview
import com.example.presentation.util.ObserveAsEvents
import com.example.presentation.util.UiText

@Composable
fun SettingsScreen(
	onBack: () -> Unit,
	viewModel: SettingsViewModel = hiltViewModel(),
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	val snackbarHostState = remember { SnackbarHostState() }
	val context = LocalContext.current

	ObserveAsEvents(viewModel.events) { event ->
		when (event) {
			is SettingsEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message.asString(context))
		}
	}

	SettingsScreenContent(
		state = state,
		snackbarHostState = snackbarHostState,
		onBack = onBack,
		onAction = viewModel::onAction,
	)
}

@Composable
private fun SettingsScreenContent(
	state: SettingsState,
	snackbarHostState: SnackbarHostState,
	onBack: () -> Unit,
	onAction: (SettingsAction) -> Unit,
) {
	if (state.openSheetEditAppVersion) {
		EditAppVersionDialog(
			versionInput = state.versionInput,
			versionInputError = state.versionInputError,
			onConfirm = { onAction(SettingsAction.SetAppVersion) },
			onDismiss = { onAction(SettingsAction.CloseEditAppVersion) },
		)
	}

	Scaffold(
		topBar = {
			SimpleTopAppBar(title = stringResource(R.string.settings_title), onBackSelected = { onBack() })
		},
		snackbarHost = { SnackbarHost(snackbarHostState) },
	) { paddingValues ->
		Column(
			modifier =
				Modifier
					.fillMaxSize()
					.padding(paddingValues)
					.padding(16.dp),
		) {
			SettingsSectionCard(
				icon = Icons.Default.Info,
				title = stringResource(R.string.settings_section_info_title),
			) { paddingValues ->
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier.fillMaxWidth(),
				) {
					Text(
						text = stringResource(R.string.settings_app_version, state.appVersion),
						style = MaterialTheme.typography.bodyMedium,
						modifier = Modifier.padding(horizontal = paddingValues),
					)

					Spacer(modifier = Modifier.weight(1f))

					IconButton(
						onClick = { onAction(SettingsAction.OpenEditAppVersion) },
					) {
						Icon(
							imageVector = Icons.Default.Edit,
							tint = MaterialTheme.colorScheme.secondary,
							contentDescription = stringResource(R.string.edit),
						)
					}
				}
			}
		}
	}
}

@Composable
private fun EditAppVersionDialog(
	versionInput: TextFieldState,
	versionInputError: UiText?,
	onConfirm: () -> Unit,
	onDismiss: () -> Unit,
) {
	val context = LocalContext.current

	AlertDialog(
		onDismissRequest = onDismiss,
		title = {
			Text(stringResource(R.string.settings_edit_version_title))
		},
		text = {
			AppTextField(
				state = versionInput,
				placeholder = stringResource(R.string.settings_edit_version_placeholder),
				singleLine = true,
				keyboardType = KeyboardType.Number,
				isError = versionInputError != null,
				supportingText = versionInputError?.asString(context),
				modifier = Modifier.fillMaxWidth(),
			)
		},
		confirmButton = {
			AppButton(
				text = stringResource(R.string.settings_edit_version_confirm),
				onClick = onConfirm,
			)
		},
		dismissButton = {
			AppButton(
				text = stringResource(R.string.cancel),
				onClick = onDismiss,
				style = AppButtonStyle.TEXT,
			)
		},
	)
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		SettingsScreenContent(
			state = SettingsState(appVersion = 1),
			snackbarHostState = SnackbarHostState(),
			onBack = {},
			onAction = {},
		)
	}
}

@ThemePreview
@Composable
private fun PreviewDialog() {
	AppTheme {
		EditAppVersionDialog(
			versionInput = TextFieldState(initialText = "3"),
			versionInputError = null,
			onConfirm = {},
			onDismiss = {},
		)
	}
}
