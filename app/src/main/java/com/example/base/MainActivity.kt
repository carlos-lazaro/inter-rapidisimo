package com.example.base

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.presentation.Presentation
import com.example.presentation.PresentationViewModel
import com.example.presentation.SessionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private val viewModel: PresentationViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		val splashScreen = installSplashScreen()
		super.onCreate(savedInstanceState)

		splashScreen.setKeepOnScreenCondition {
			viewModel.session.value == SessionState.Loading
		}

		splashScreen.setOnExitAnimationListener { viewProvider ->
			AnimatorSet().apply {
				interpolator = AccelerateDecelerateInterpolator()
				duration = 200L
				playTogether(
					ObjectAnimator.ofFloat(viewProvider.iconView, "scaleX", 1f, 0f),
					ObjectAnimator.ofFloat(viewProvider.iconView, "scaleY", 1f, 0f),
					ObjectAnimator.ofFloat(viewProvider.view, "alpha", 1f, 0f),
				)
				doOnEnd { viewProvider.remove() }
				start()
			}
		}

		enableEdgeToEdge()
		setContent {
			Presentation(viewModel = viewModel)
		}
	}
}
