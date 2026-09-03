package com.example.thestory.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.material3.MaterialTheme
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

/** One softly drifting heart in the background sky. */
private data class DriftingHeart(
    val startX: Float,
    val startY: Float,
    val speed: Float,
    val size: Float,
    val swayAmplitude: Float,
    val swayFrequency: Float,
    val phase: Float,
    val rotation: Float,
    val alpha: Float
)

/**
 * A dreamy background of rose hearts drifting slowly upwards,
 * swaying like they are floating on a gentle breeze.
 * Pure Compose canvas — no assets required.
 */
@Composable
fun FloatingHeartsBackground(modifier: Modifier = Modifier) {
    val hearts = remember {
        List(16) {
            DriftingHeart(
                startX = Random.nextFloat(),
                startY = Random.nextFloat(),
                speed = 0.03f + Random.nextFloat() * 0.05f,
                size = 14f + Random.nextFloat() * 30f,
                swayAmplitude = 8f + Random.nextFloat() * 22f,
                swayFrequency = 0.3f + Random.nextFloat() * 0.8f,
                phase = Random.nextFloat() * (2f * PI.toFloat()),
                rotation = -20f + Random.nextFloat() * 40f,
                alpha = 0.05f + Random.nextFloat() * 0.09f
            )
        }
    }
    val transition = rememberInfiniteTransition(label = "drift")
    val time by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 30000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "driftTime"
    )
    val rose = MaterialTheme.colorScheme.primary
    val blush = MaterialTheme.colorScheme.secondary

    Canvas(modifier = modifier) {
        hearts.forEach { heart ->
            // Rise from bottom (fraction 1) to top (fraction 0), wrapping around.
            val fraction = ((heart.startY - time * heart.speed) % 1f + 1f) % 1f
            val y = fraction * size.height
            val x = heart.startX * size.width +
                sin(time * 2f * PI.toFloat() * heart.swayFrequency + heart.phase) *
                heart.swayAmplitude
            // Fade softly at both edges of the screen.
            val edgeFade = sin(fraction * PI.toFloat()).coerceIn(0f, 1f)
            val alpha = heart.alpha * edgeFade
            if (alpha > 0.005f) {
                drawDriftingHeart(
                    center = Offset(x, y),
                    size = heart.size,
                    rotation = heart.rotation + sin(heart.phase + time * 4f) * 10f,
                    color = if (heart.size > 28f) blush else rose,
                    alpha = alpha
                )
            }
        }
    }
}

private fun DrawScope.drawDriftingHeart(
    center: Offset,
    size: Float,
    rotation: Float,
    color: Color,
    alpha: Float
) {
    val path = Path().apply {
        val s = size
        moveTo(center.x, center.y + 0.35f * s)
        cubicTo(
            center.x - 0.55f * s, center.y - 0.05f * s,
            center.x - 0.35f * s, center.y - 0.45f * s,
            center.x, center.y - 0.15f * s
        )
        cubicTo(
            center.x + 0.35f * s, center.y - 0.45f * s,
            center.x + 0.55f * s, center.y - 0.05f * s,
            center.x, center.y + 0.35f * s
        )
        close()
    }
    rotate(degrees = rotation, pivot = center) {
        drawPath(
            path = path,
            brush = Brush.radialGradient(
                colors = listOf(color.copy(alpha = alpha * 1.6f), color.copy(alpha = alpha * 0.4f)),
                center = center,
                radius = size
            )
        )
    }
}
