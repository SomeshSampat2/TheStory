package com.example.thestory.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * The Story always uses its own romantic midnight-rose palette so the
 * reading experience feels like a candle-lit storybook, day or night.
 */
private val StoryColorScheme = darkColorScheme(
    primary = StoryRose,
    onPrimary = StoryInk,
    primaryContainer = StoryRoseDeep,
    onPrimaryContainer = StoryCream,
    secondary = StoryBlush,
    onSecondary = StoryInk,
    tertiary = StoryGold,
    background = StoryInk,
    onBackground = StoryCream,
    surface = StorySurface,
    onSurface = StoryCream,
    surfaceVariant = StorySurfaceHigh,
    onSurfaceVariant = StoryLavender,
    outline = StoryLavender.copy(alpha = 0.4f)
)

private val FallbackLightScheme = lightColorScheme(
    primary = StoryRoseDeep,
    secondary = StoryBlush,
    tertiary = StoryGold,
    background = StoryCream,
    onBackground = StoryInk
)

@Composable
fun TheStoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // The story always reads best in its candle-lit midnight palette.
    MaterialTheme(
        colorScheme = if (darkTheme) StoryColorScheme else FallbackLightScheme,
        typography = StoryTypography,
        content = content
    )
}
