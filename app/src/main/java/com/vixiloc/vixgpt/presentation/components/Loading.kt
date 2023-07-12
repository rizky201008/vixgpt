package com.vixiloc.vixgpt.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vixiloc.vixgpt.R

@Composable
fun ChatLoading(modifier: Modifier) {
    var isPlaying by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.animation_ljy8f6rh
            )
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
            isPlaying = isPlaying
        )

        LaunchedEffect(key1 = progress) {
            if (progress == 0f) {
                isPlaying = true
            }
            if (progress == 1f) {
                isPlaying = false
            }
        }

        LaunchedEffect(key1 = isPlaying) {
            isPlaying = true
        }

        LottieAnimation(
            modifier = modifier,
            composition = composition,
            progress = { progress }
        )
    }
}