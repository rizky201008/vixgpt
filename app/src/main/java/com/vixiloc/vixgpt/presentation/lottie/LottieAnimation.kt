package com.vixiloc.vixgpt.presentation.lottie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.LottieAnimation as LottieAnimationCompose

@Composable
fun LottieAnimation(modifier: Modifier, resource: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource))
    var isPlaying by remember { mutableStateOf(true) }
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
    LottieAnimationCompose(
        modifier = modifier,
        composition = composition,
        progress = { progress }
    )
}