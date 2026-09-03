package com.example.thestory.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.annotation.StringRes
import com.example.thestory.R
import com.example.thestory.data.StoryProgressStore
import com.example.thestory.data.StoryRepository
import com.example.thestory.ui.components.FloatingHeartsBackground
import com.example.thestory.ui.components.LottieAnimationBox
import com.example.thestory.ui.theme.StoryGold
import com.example.thestory.ui.theme.StoryLavender
import kotlinx.coroutines.delay

/**
 * One page of the story: the chapter opens with a Lottie scene,
 * then the title and the body text fade in.
 */
@Composable
fun ChapterDetailScreen(
    chapterId: Int,
    onBack: () -> Unit
) {
    val chapter = remember(chapterId) { StoryRepository.chapterById(chapterId) }

    if (chapter == null) {
        // Unknown chapter — go back to the shelf.
        LaunchedEffect(Unit) { onBack() }
        return
    }

    val totalChapters = StoryRepository.chapters.size

    // Remember that this chapter has been read — its heart fills in on the shelf.
    val context = LocalContext.current
    LaunchedEffect(chapter.id) {
        StoryProgressStore.markRead(context, chapter.id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FloatingHeartsBackground(modifier = Modifier.fillMaxSize())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            // ---- Whole screen scrolls: top bar, title, card and all ----
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // ---- Top bar ----
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                // ---- Reading area ----
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(chapter.titleRes),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    AnimatedParagraph(textRes = chapter.bodyRes)
                    if (chapter.id == totalChapters) {
                        Spacer(modifier = Modifier.height(32.dp))
                        TheEndSection()
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
    }
}

/**
 * The chapter body sits on a soft card with a shadow, and the whole
 * card fades and floats in.
 */
@Composable
private fun AnimatedParagraph(@StringRes textRes: Int) {
    val appear = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(180L)
        appear.animateTo(1f, tween(durationMillis = 850, easing = FastOutSlowInEasing))
    }
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                alpha = appear.value
                translationY = (1f - appear.value) * 45f
            }
    ) {
        Text(
            text = stringResource(textRes),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.92f),
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
private fun TheEndSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        LottieAnimationBox(
            lottieRes = R.raw.sparkle_stars,
            modifier = Modifier
                .size(120.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.the_end_title),
            style = MaterialTheme.typography.titleLarge,
            color = StoryGold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.the_end_message),
            style = MaterialTheme.typography.bodyMedium,
            color = StoryLavender,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}
