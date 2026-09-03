package com.example.thestory.data.model

import androidx.annotation.StringRes

/**
 * One chapter of the story. All visible text comes from string resources
 * so the whole story can be written inside res/values/strings.xml.
 */
data class StoryChapter(
    val id: Int,
    @StringRes val titleRes: Int,
    @StringRes val bodyRes: Int
)