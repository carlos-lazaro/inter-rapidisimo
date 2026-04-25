package com.example.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.component.button.AppButton
import com.example.presentation.component.textfields.AppPasswordTextField
import com.example.presentation.component.textfields.AppTextField
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.preview.ThemePreview
import com.example.presentation.util.ObserveAsEvents

@Composable
fun LoginScreen(
	onLoginSuccess: () -> Unit,
	viewModel: LoginViewModel = hiltViewModel(),
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	val snackbarHostState = remember { SnackbarHostState() }
	val context = LocalContext.current

	ObserveAsEvents(viewModel.events) { event ->
		when (event) {
			is LoginEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message.asString(context))
			LoginEvent.NavigateToHome -> onLoginSuccess()
		}
	}

	LoginScreenContent(
		state = state,
		snackbarHostState = snackbarHostState,
		onAction = viewModel::onAction,
	)
}

@Composable
private fun LoginScreenContent(
	state: LoginState,
	snackbarHostState: SnackbarHostState,
	onAction: (LoginAction) -> Unit,
) {
	val context = LocalContext.current
	val padding = 16.dp

	Scaffold(
		snackbarHost = { SnackbarHost(snackbarHostState) },
	) { innerPadding ->
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier =
				Modifier
					.fillMaxSize()
					.padding(innerPadding)
					.padding(horizontal = padding),
		) {
			Spacer(modifier = Modifier.weight(1f))
			Image(
				painter = painterResource(R.drawable.logo_app),
				contentDescription = stringResource(R.string.logo),
				modifier = Modifier.size(100.dp).clip(MaterialTheme.shapes.large),
			)
			Spacer(modifier = Modifier.weight(1f))

			AppTextField(
				state = state.username,
				placeholder = stringResource(R.string.login_usuario_label),
				isError = state.hasUsernameError,
				supportingText = state.usernameError?.asString(context),
			)
			Spacer(modifier = Modifier.height(padding))
			AppPasswordTextField(
				state = state.password,
				placeholder = stringResource(R.string.login_password_label),
				isPasswordVisible = state.isPasswordVisible,
				onToggleVisibilityClick = { onAction(LoginAction.TogglePasswordVisible) },
				isError = state.hasPasswordError,
				supportingText = state.passwordError?.asString(context),
			)
			Spacer(modifier = Modifier.height(padding))
			Spacer(modifier = Modifier.height(padding))
			AppButton(
				text = stringResource(R.string.login_button),
				onClick = { onAction(LoginAction.Submit) },
				isLoading = state.isLoading,
				modifier = Modifier.fillMaxWidth(),
			)
			Spacer(modifier = Modifier.weight(1f))
		}
	}
}

@ThemePreview
@Composable
private fun Preview() {
	AppTheme {
		LoginScreenContent(
			state = LoginState(),
			snackbarHostState = remember { SnackbarHostState() },
			onAction = {},
		)
	}
}
