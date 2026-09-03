package com.example.thestory.ui.components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.thestory.R

/**
 * Renders a looping Lottie animation from a raw resource.
 * If the resource is null or fails to load, a pure-Compose
 * pulsing heart is shown instead, so the app always looks alive.
 */
@Composable
fun LottieAnimationBox(
    @RawRes lottieRes: Int?,
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever
) {
    if (lottieRes != null) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieRes))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = iterations
        )
        if (composition != null) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = modifier
            )
        } else {
            Box(modifier = modifier, contentAlignment = Alignment.Center) {
                PulsingHeartIcon(modifier = Modifier.size(56.dp))
            }
        }
    } else {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            PulsingHeartIcon(modifier = Modifier.size(56.dp))
        }
    }
}
