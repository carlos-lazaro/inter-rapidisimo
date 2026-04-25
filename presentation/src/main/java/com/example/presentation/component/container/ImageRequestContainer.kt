package com.example.presentation.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun ImageRequestContainer(
	imageUrl: String,
	modifier: Modifier = Modifier,
	contentScale: ContentScale = ContentScale.Fit,
) {
	val isPreview = LocalInspectionMode.current

	if (isPreview) {
		Box(
			modifier =
				modifier
					.background(Color.LightGray),
		)
		return
	}

	val context = LocalContext.current
	var retryHash by remember { mutableIntStateOf(0) }
	var imageState: AsyncImagePainter.State by remember { mutableStateOf(AsyncImagePainter.State.Empty) }
	val imageRequest =
		remember(imageUrl, retryHash) {
			ImageRequest
				.Builder(context)
				.data("$imageUrl?retry=$retryHash")
				.crossfade(true)
				.build()
		}

	Box(
		modifier =
			modifier
				.fillMaxSize(),
	) {
		AsyncImage(
			model = imageRequest,
			contentDescription = null,
			contentScale = contentScale,
			onState = { state ->
				imageState = state
			},
			modifier =
				Modifier
					.fillMaxSize()
					.background(MaterialTheme.colorScheme.background),
		)

		when (imageState) {
			is AsyncImagePainter.State.Loading -> {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}

			is AsyncImagePainter.State.Success -> {}

			is AsyncImagePainter.State.Error -> {
				Box(
					modifier =
						Modifier
							.fillMaxSize()
							.background(MaterialTheme.colorScheme.tertiaryContainer),
				)

				IconButton(
					onClick = { retryHash++ },
					modifier = Modifier.align(Alignment.Center),
				) {
					Icon(
						Icons.Default.Refresh,
						null,
						tint = MaterialTheme.colorScheme.onTertiaryContainer,
					)
				}
			}

			is AsyncImagePainter.State.Empty -> {}
		}
	}
}
