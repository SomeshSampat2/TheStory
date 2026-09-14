package com.example.thestory.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * One chapter of the story. All visible text comes from string resources
 * so the whole story can be written inside res/values/strings.xml.
 *
 * Two layouts are supported:
 * - Text-only (chapters 1-6, 8-15): set [bodyRes], leave the rest empty.
 * - Text + photos (chapter 7 and any future chapter): set [bodyPart1Res],
 *   [imageResIds] and optionally [bodyPart2Res]. The UI renders
 *   body_1 -> horizontal photo strip -> body_2 (blank parts are hidden).
 * - Text + two photo strips (chapter 7): additionally set
 *   [secondImageResIds] and optionally [bodyPart3Res]. The UI renders
 *   body_1 -> strip 1 -> body_2 -> strip 2 -> body_3.
 */
data class StoryChapter(
    val id: Int,
    @StringRes val titleRes: Int,
    @StringRes val bodyRes: Int? = null,
    @StringRes val bodyPart1Res: Int? = null,
    @DrawableRes val imageResIds: List<Int> = emptyList(),
    @StringRes val bodyPart2Res: Int? = null,
    @DrawableRes val secondImageResIds: List<Int> = emptyList(),
    @StringRes val bodyPart3Res: Int? = null
) {
    val hasImages: Boolean get() = imageResIds.isNotEmpty() || secondImageResIds.isNotEmpty()
}