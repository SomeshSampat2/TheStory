package com.example.thestory.data

import com.example.thestory.R
import com.example.thestory.data.model.StoryChapter

/**
 * The heart of the app: the list of chapters, in order.
 *
 * ➤ To write YOUR story: edit the chapter_* strings in
 *   app/src/main/res/values/strings.xml
 * ➤ To add more chapters: add a new StoryChapter here (id must be
 *   sequential) and the matching strings — the UI adapts automatically.
 */
object StoryRepository {

    val chapters: List<StoryChapter> = listOf(
        StoryChapter(
            id = 1,
            titleRes = R.string.chapter_one_title,
            bodyRes = R.string.chapter_one_body
        ),
        StoryChapter(
            id = 2,
            titleRes = R.string.chapter_two_title,
            bodyRes = R.string.chapter_two_body
        ),
        StoryChapter(
            id = 3,
            titleRes = R.string.chapter_three_title,
            bodyRes = R.string.chapter_three_body
        ),
        StoryChapter(
            id = 4,
            titleRes = R.string.chapter_four_title,
            bodyRes = R.string.chapter_four_body
        ),
        StoryChapter(
            id = 5,
            titleRes = R.string.chapter_five_title,
            bodyRes = R.string.chapter_five_body
        ),
        StoryChapter(
            id = 6,
            titleRes = R.string.chapter_six_title,
            bodyRes = R.string.chapter_six_body
        )
    )

    fun chapterById(id: Int): StoryChapter? = chapters.firstOrNull { it.id == id }
}
