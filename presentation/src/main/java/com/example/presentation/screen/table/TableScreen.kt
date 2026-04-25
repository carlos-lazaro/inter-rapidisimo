package com.example.presentation.screen.table

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.domain.table.model.Table
import com.example.presentation.R
import com.example.presentation.component.SimpleTopAppBar
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview
import com.example.presentation.util.ObserveAsEvents

@Composable
fun TableScreen(
	onBack: () -> Unit,
	viewModel: TableViewModel = hiltViewModel(),
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	val snackbarHostState = remember { SnackbarHostState() }
	val context = LocalContext.current

	ObserveAsEvents(viewModel.events) { event ->
		when (event) {
			is TableEvent.ShowSnackbar -> {
				snackbarHostState.currentSnackbarData?.dismiss()
				snackbarHostState.showSnackbar(event.message.asString(context))
			}
		}
	}

	TableScreenContent(
		state = state,
		snackbarHostState = snackbarHostState,
		onAction = viewModel::onAction,
		onBack = onBack,
	)
}

@Composable
private fun TableScreenContent(
	state: TableState,
	snackbarHostState: SnackbarHostState,
	onAction: (TableAction) -> Unit,
	onBack: () -> Unit,
) {
	val padding = 16.dp
	Scaffold(
		snackbarHost = { SnackbarHost(snackbarHostState) },
		topBar = {
			SimpleTopAppBar(
				title = stringResource(R.string.tables),
				onBackSelected = onBack,
			)
		},
	) { innerPadding ->
		PullToRefreshBox(
			isRefreshing = state.isLoading,
			onRefresh = { onAction(TableAction.Refresh) },
			modifier =
				Modifier
					.fillMaxSize()
					.padding(innerPadding),
		) {
			LazyColumn(
				verticalArrangement = Arrangement.spacedBy(padding / 2),
				modifier =
					Modifier
						.fillMaxSize(),
			) {
				item {
					Spacer(modifier = Modifier.size(padding))
				}
				items(items = state.tables, key = { it.tableName }) { table ->
					TableCard(table)
				}
				item {
					Spacer(modifier = Modifier.size(padding))
				}
			}
		}
	}
}

@Composable
private fun TableCard(table: Table) {
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
			Text(text = table.tableName, style = MaterialTheme.typography.titleMedium)

			Spacer(modifier = Modifier.size(padding / 2))

			Text(
				text = stringResource(R.string.table_pk, table.pk),
				style = MaterialTheme.typography.bodySmall,
				color = MaterialTheme.colorScheme.onSurfaceVariant,
			)
			Text(
				text = stringResource(R.string.table_batch_size, table.batchSize),
				style = MaterialTheme.typography.bodySmall,
				color = MaterialTheme.colorScheme.onSurfaceVariant,
			)
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		TableScreenContent(
			state =
				TableState(
					tables =
						listOf(
							Table(tableName = "usuarios", pk = "id", batchSize = 100),
							Table(tableName = "productos", pk = "codigo", batchSize = 50),
						),
				),
			snackbarHostState = remember { SnackbarHostState() },
			onAction = {},
			onBack = {},
		)
	}
}
