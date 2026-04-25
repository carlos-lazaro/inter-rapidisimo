package com.example.presentation.screen.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.onFailure
import com.example.domain.table.usecase.GetTablesUseCase
import com.example.domain.table.usecase.SyncTablesUseCase
import com.example.presentation.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject

@HiltViewModel
class TableViewModel
	@Inject
	constructor(
		private val getTablesUseCase: GetTablesUseCase,
		private val syncTablesUseCase: SyncTablesUseCase,
	) : ViewModel() {
		private var hasLoadedInitialData = false
		private val syncMutex = Mutex()

		private val _state = MutableStateFlow(TableState())
		val state =
			_state
				.onStart {
					if (!hasLoadedInitialData) {
						sync()
						observe()
						hasLoadedInitialData = true
					}
				}.stateIn(
					scope = viewModelScope,
					started = SharingStarted.WhileSubscribed(5_000L),
					initialValue = _state.value,
				)

		private val _events = Channel<TableEvent>()
		val events = _events.receiveAsFlow()

		fun onAction(action: TableAction) {
			when (action) {
				TableAction.Refresh -> sync()
			}
		}

		private fun sync() {
			if (!syncMutex.tryLock()) return

			_state.update { it.copy(isLoading = true) }

			viewModelScope.launch {
				try {
					syncTablesUseCase()
						.onFailure { _events.send(TableEvent.ShowSnackbar(it.asUiText())) }
				} finally {
					_state.update { it.copy(isLoading = false) }
					syncMutex.unlock()
				}
			}
		}

		private fun observe() {
			viewModelScope.launch {
				getTablesUseCase().collect { items ->
					_state.update { it.copy(tables = items) }
				}
			}
		}
	}
