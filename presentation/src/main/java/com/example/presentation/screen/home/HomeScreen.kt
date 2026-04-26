@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.auth.model.User
import com.example.presentation.R
import com.example.presentation.component.button.AppButton
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview
import com.example.presentation.util.ObserveAsEvents

@Composable
fun HomeScreen(
	goLocations: () -> Unit,
	goTables: () -> Unit,
	goSettings: () -> Unit,
	viewModel: HomeViewModel = hiltViewModel(),
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	val snackbarHostState = remember { SnackbarHostState() }
	val context = LocalContext.current

	ObserveAsEvents(viewModel.events) { event ->
		when (event) {
			is HomeEvent.ShowSnackbar -> {
				snackbarHostState.currentSnackbarData?.dismiss()
				snackbarHostState.showSnackbar(event.message.asString(context))
			}
		}
	}

	HomeScreenContent(
		state = state,
		snackbarHostState = snackbarHostState,
		goLocations = goLocations,
		goTables = goTables,
		goSettings = goSettings,
		onAction = viewModel::onAction,
	)
}

@Composable
private fun HomeScreenContent(
	state: HomeState,
	snackbarHostState: SnackbarHostState,
	goLocations: () -> Unit,
	goTables: () -> Unit,
	goSettings: () -> Unit,
	onAction: (HomeAction) -> Unit,
) {
	val padding = 16.dp

	Scaffold(
		snackbarHost = { SnackbarHost(snackbarHostState) },
		topBar = {
			TopAppBar(
				title = { Text(stringResource(R.string.welcome)) },
				navigationIcon = {
					IconButton(
						onClick = { onAction(HomeAction.Logout) },
						enabled = !state.isLoggingOut,
					) {
						Icon(
							Icons.AutoMirrored.Filled.ExitToApp,
							contentDescription = stringResource(R.string.logout),
							tint = MaterialTheme.colorScheme.onBackground,
						)
					}
				},
				actions = {
					IconButton(onClick = goSettings) {
						Icon(
							Icons.Default.Settings,
							contentDescription = stringResource(R.string.settings_title),
							tint = MaterialTheme.colorScheme.onBackground,
						)
					}
				},
				colors =
					TopAppBarDefaults.topAppBarColors(
						containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
						titleContentColor = MaterialTheme.colorScheme.primary,
					),
			)
		},
		bottomBar = {
			BottomAppBar {
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier =
						Modifier
							.fillMaxWidth()
							.padding(padding),
				) {
					AppButton(
						text = stringResource(R.string.locations),
						onClick = goLocations,
						modifier = Modifier.weight(1f),
					)

					Spacer(modifier = Modifier.size(padding))

					AppButton(
						text = stringResource(R.string.tables),
						onClick = goTables,
						modifier = Modifier.weight(1f),
					)
				}
			}
		},
	) { innerPadding ->
		Column(
			modifier =
				Modifier
					.fillMaxSize()
					.padding(innerPadding)
					.padding(padding),
		) {
			Text(
				text =
					buildAnnotatedString {
						withStyle(
							style =
								SpanStyle(
									fontWeight = FontWeight.Bold,
									fontSize = MaterialTheme.typography.headlineMedium.fontSize,
								),
						) {
							append(stringResource(R.string.hi))
						}

						state.user?.name?.let { name ->
							withStyle(
								style =
									SpanStyle(
										fontWeight = FontWeight.Bold,
										fontSize = MaterialTheme.typography.headlineMedium.fontSize,
									),
							) {
								append(", ")
								append(name)
							}
						}
						state.user?.username?.let { username ->
							withStyle(
								style =
									SpanStyle(
										fontWeight = FontWeight.Normal,
									),
							) {
								append(", ")
								append(username)
							}
						}
						state.user?.identification?.let { identification ->
							withStyle(
								style =
									SpanStyle(
										fontWeight = FontWeight.Light,
									),
							) {
								append(", ")
								append(identification)
							}
						}
					},
				style = MaterialTheme.typography.bodyLarge,
			)

			Spacer(modifier = Modifier.weight(1f))
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		HomeScreenContent(
			state =
				HomeState(
					user =
						User(
							username = "john_doe",
							name = "John Doe",
							identification = "123456789",
						),
				),
			snackbarHostState = remember { SnackbarHostState() },
			goLocations = {},
			goTables = {},
			goSettings = {},
			onAction = {},
		)
	}
}
