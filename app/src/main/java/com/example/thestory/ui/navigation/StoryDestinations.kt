package com.example.thestory.ui.navigation

/**
 * All the places this story can take you.
 */
object StoryDestinations {
    const val SPLASH_ROUTE = "splash"
    const val CHAPTERS_ROUTE = "chapters"
    const val CHAPTER_DETAIL_ROUTE = "chapter/{chapterId}"

    const val ARG_CHAPTER_ID = "chapterId"

    fun chapterDetailRoute(chapterId: Int): String = "chapter/$chapterId"
}
