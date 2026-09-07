package com.example.thestory.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.thestory.R

/**
 * Horizontal strip of small chapter photos.
 *
 * Reusable for ANY chapter: pass that chapter's drawable ids.
 * Tapping a thumbnail opens it fullscreen (pinch-to-zoom, swipe via arrows).
 * Renders nothing when [imageResIds] is empty, so text-only chapters are unaffected.
 */
@Composable
fun ChapterImageGallery(
    @DrawableRes imageResIds: List<Int>,
    modifier: Modifier = Modifier
) {
    if (imageResIds.isEmpty()) return

    var fullscreenIndex by remember { mutableStateOf<Int?>(null) }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        itemsIndexed(imageResIds, key = { index, res -> "$index-$res" }) { index, resId ->
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .width(120.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { fullscreenIndex = index }
            ) {
                Image(
                    painter = painterResource(resId),
                    contentDescription = stringResource(R.string.cd_chapter_image, index + 1),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    val openIndex = fullscreenIndex
    if (openIndex != null) {
        FullscreenGalleryViewer(
            imageResIds = imageResIds,
            initialIndex = openIndex,
            onDismiss = { fullscreenIndex = null }
        )
    }
}

/**
 * Fullscreen viewer: black scrim, pinch-to-zoom image, counter,
 * middle-edge previous/next arrows and a close button.
 */
@Composable
private fun FullscreenGalleryViewer(
    @DrawableRes imageResIds: List<Int>,
    initialIndex: Int,
    onDismiss: () -> Unit
) {
    var currentIndex by remember(initialIndex) { mutableIntStateOf(initialIndex) }
    val currentRes = imageResIds[currentIndex.coerceIn(imageResIds.indices)]

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.96f))
                .statusBarsPadding()
        ) {
            // Zoomable image, centered. Zoom resets when paging via key().
            key(currentRes) {
                var scale by remember { mutableFloatStateOf(1f) }
                var offset by remember { mutableStateOf(Offset.Zero) }

                Image(
                    painter = painterResource(currentRes),
                    contentDescription = stringResource(R.string.cd_chapter_image, currentIndex + 1),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 72.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            translationX = offset.x
                            translationY = offset.y
                        }
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, zoom, _ ->
                                scale = (scale * zoom).coerceIn(1f, 5f)
                                offset = if (scale <= 1f) Offset.Zero else offset + pan
                            }
                        }
                )
            }

            // Top bar: counter + close.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${currentIndex + 1} / ${imageResIds.size}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp)
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.cd_close_image),
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Prev / next arrows at middle-left / middle-right.
            // No wrap-around: prev hidden on first photo, next hidden on last.
            if (currentIndex > 0) {
                IconButton(
                    onClick = { currentIndex -= 1 },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 4.dp)
                        .background(
                            Color.Black.copy(alpha = 0.45f),
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous photo",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            if (currentIndex < imageResIds.lastIndex) {
                IconButton(
                    onClick = { currentIndex += 1 },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 4.dp)
                        .background(
                            Color.Black.copy(alpha = 0.45f),
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next photo",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}
