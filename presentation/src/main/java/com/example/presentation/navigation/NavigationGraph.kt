package com.example.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.presentation.screen.home.HomeScreen
import com.example.presentation.screen.location.LocationScreen
import com.example.presentation.screen.login.LoginScreen
import com.example.presentation.screen.settings.SettingsScreen
import com.example.presentation.screen.table.TableScreen

@Composable
fun NavigationGraph(startScreen: Screen) {
	val backStack = rememberNavBackStack(startScreen)

	NavDisplay(
		backStack = backStack,
		onBack = { backStack.removeLastOrNull() },
		transitionSpec = {
			slideInHorizontally(
				initialOffsetX = { it },
				animationSpec = tween(600),
			) togetherWith ExitTransition.KeepUntilTransitionsFinished
		},
		popTransitionSpec = {
			EnterTransition.None togetherWith
				slideOutHorizontally(
					targetOffsetX = { it },
					animationSpec = tween(300),
				)
		},
		predictivePopTransitionSpec = {
			EnterTransition.None togetherWith
				slideOutHorizontally(
					targetOffsetX = { it },
					animationSpec = tween(300),
				)
		},
		entryDecorators =
			listOf(
				rememberSaveableStateHolderNavEntryDecorator(),
				rememberViewModelStoreNavEntryDecorator(),
			),
		entryProvider =
			entryProvider {
				entry<Screen.Login> {
					LoginScreen(
						onLoginSuccess = dropUnlessResumed { backStack.add(Screen.Home) },
					)
				}
				entry<Screen.Home> {
					HomeScreen(
						goLocations = dropUnlessResumed { backStack.add(Screen.Locations) },
						goTables = dropUnlessResumed { backStack.add(Screen.Tables) },
						goSettings = dropUnlessResumed { backStack.add(Screen.Setting) },
					)
				}
				entry<Screen.Locations> {
					LocationScreen(onBack = dropUnlessResumed { backStack.removeLastOrNull() })
				}
				entry<Screen.Tables> {
					TableScreen(onBack = dropUnlessResumed { backStack.removeLastOrNull() })
				}
				entry<Screen.Setting> {
					SettingsScreen(onBack = dropUnlessResumed { backStack.removeLastOrNull() })
				}
			},
	)
}
