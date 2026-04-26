package com.example.presentation.screen.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.location.model.Location
import com.example.presentation.R
import com.example.presentation.component.emptyState.EmptyStateView
import com.example.presentation.component.topBar.SimpleTopAppBar
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview
import com.example.presentation.util.ObserveAsEvents

@Composable
fun LocationScreen(
	onBack: () -> Unit,
	viewModel: LocationViewModel = hiltViewModel(),
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	val snackbarHostState = remember { SnackbarHostState() }
	val context = LocalContext.current

	ObserveAsEvents(viewModel.events) { event ->
		when (event) {
			is LocationEvent.ShowSnackbar -> {
				snackbarHostState.currentSnackbarData?.dismiss()
				snackbarHostState.showSnackbar(event.message.asString(context))
			}
		}
	}

	LocationScreenContent(
		state = state,
		snackbarHostState = snackbarHostState,
		onAction = viewModel::onAction,
		onBack = onBack,
	)
}

@Composable
private fun LocationScreenContent(
	state: LocationState,
	snackbarHostState: SnackbarHostState,
	onAction: (LocationAction) -> Unit,
	onBack: () -> Unit,
) {
	val padding = 16.dp

	Scaffold(
		snackbarHost = { SnackbarHost(snackbarHostState) },
		topBar = {
			SimpleTopAppBar(
				title = stringResource(R.string.locations),
				onBackSelected = onBack,
			)
		},
	) { innerPadding ->
		PullToRefreshBox(
			isRefreshing = state.isLoading,
			onRefresh = { onAction(LocationAction.Refresh) },
			modifier =
				Modifier
					.fillMaxSize()
					.padding(innerPadding),
		) {
			if (state.locations.isEmpty()) {
				EmptyStateView(
					isLoading = state.isLoading,
					retry = { onAction(LocationAction.Refresh) },
					modifier = Modifier.fillMaxSize(),
				)
			} else {
				LazyColumn(
					verticalArrangement = Arrangement.spacedBy(padding / 2),
					modifier = Modifier.fillMaxSize(),
				) {
					item {
						Spacer(modifier = Modifier.size(padding))
					}
					items(items = state.locations, key = { it.idLocalidad }) { location ->
						LocationCard(location)
					}
					item {
						Spacer(modifier = Modifier.size(padding))
					}
				}
			}
		}
	}
}

@Composable
private fun LocationCard(location: Location) {
	val padding = 16.dp

	ElevatedCard(
		modifier =
			Modifier
				.fillMaxWidth()
				.padding(horizontal = padding),
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(4.dp),
			modifier = Modifier.padding(padding),
		) {
			Text(
				text = location.nombreCompleto ?: location.nombre ?: location.idLocalidad,
				style = MaterialTheme.typography.titleMedium,
			)

			Spacer(modifier = Modifier.size(padding / 2))

			Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
				location.abreviacionCiudad?.let {
					Text(
						text = it,
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.primary,
					)
				}
				location.nombreZona?.let {
					Text(
						text = it,
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.onSurfaceVariant,
					)
				}
				location.codigoPostal?.let {
					Text(
						text = it,
						style = MaterialTheme.typography.bodySmall,
						color = MaterialTheme.colorScheme.onSurfaceVariant,
					)
				}
			}
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		LocationScreenContent(
			state =
				LocationState(
					locations =
						listOf(
							Location(
								idLocalidad = "BOG",
								nombre = "Bogotá",
								nombreCorto = "BOG",
								nombreZona = "Zona Centro",
								codigoPostal = "110111",
								nombreCompleto = "Bogotá D.C.",
								abreviacionCiudad = "BOG",
							),
							Location(
								idLocalidad = "MED",
								nombre = "Medellín",
								nombreCorto = "MED",
								nombreZona = null,
								codigoPostal = "050001",
								nombreCompleto = "Medellín, Antioquia",
								abreviacionCiudad = "MED",
							),
						),
				),
			snackbarHostState = remember { SnackbarHostState() },
			onAction = {},
			onBack = {},
		)
	}
}

@ThemePreview
@Composable
private fun PreviewEmpty() {
	AppTheme {
		LocationScreenContent(
			state = LocationState(locations = emptyList(), isLoading = false),
			snackbarHostState = remember { SnackbarHostState() },
			onAction = {},
			onBack = {},
		)
	}
}

@ThemePreview
@Composable
private fun PreviewLocationCard() {
	AppTheme {
		LocationCard(
			location =
				Location(
					idLocalidad = "BOG",
					nombre = "Bogotá",
					nombreCorto = "BOG",
					nombreZona = "Zona Centro",
					codigoPostal = "110111",
					nombreCompleto = "Bogotá D.C.",
					abreviacionCiudad = "BOG",
				),
		)
	}
}
