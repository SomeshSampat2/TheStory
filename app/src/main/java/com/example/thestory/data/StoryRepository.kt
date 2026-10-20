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
 * ➤ To add photos to any chapter later:
 *   1. Drop lowercase drawables (e.g. chapter_8_chat_1.jpeg) into res/drawable.
 *   2. Split its text into chapter_*_body_1 / chapter_*_body_2 strings.
 *   3. Switch that entry to bodyPart1Res + imageResIds + bodyPart2Res
 *      (see chapter 7 below). Chapters without imageResIds stay text-only.
 * ➤ Chapter 7 has THREE strips: body_1 -> strip 1 (chapter_7_chat_*)
 *   -> body_2 -> strip 2 (chapter_7_second_chat_*) -> body_3
 *   -> strip 3 (chapter_7_third_chat_*) -> body_4.
 * ➤ Chapter 8 has ONE strip: body_1 -> strip (insta_1..18) -> body_2.
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
        ),
        StoryChapter(
            id = 7,
            titleRes = R.string.chapter_seven_title,
            bodyPart1Res = R.string.chapter_seven_body_1,
            imageResIds = listOf(
                R.drawable.chapter_7_chat_1,
                R.drawable.chapter_7_chat_2,
                R.drawable.chapter_7_chat_3,
                R.drawable.chapter_7_chat_4,
                R.drawable.chapter_7_chat_5,
                R.drawable.chapter_7_chat_6
            ),
            bodyPart2Res = R.string.chapter_seven_body_2,
            secondImageResIds = listOf(
                R.drawable.chapter_7_second_chat_1,
                R.drawable.chapter_7_second_chat_2,
                R.drawable.chapter_7_second_chat_3,
                R.drawable.chapter_7_second_chat_4,
                R.drawable.chapter_7_second_chat_5,
                R.drawable.chapter_7_second_chat_6,
                R.drawable.chapter_7_second_chat_7,
                R.drawable.chapter_7_second_chat_8,
                R.drawable.chapter_7_second_chat_9,
                R.drawable.chapter_7_second_chat_10,
                R.drawable.chapter_7_second_chat_11,
                R.drawable.chapter_7_second_chat_12,
                R.drawable.chapter_7_second_chat_13,
                R.drawable.chapter_7_second_chat_14,
                R.drawable.chapter_7_second_chat_15,
                R.drawable.chapter_7_second_chat_16,
                R.drawable.chapter_7_second_chat_17,
                R.drawable.chapter_7_second_chat_18
            ),
            bodyPart3Res = R.string.chapter_seven_body_3,
            thirdImageResIds = listOf(
                R.drawable.chapter_7_third_chat_1,
                R.drawable.chapter_7_third_chat_2,
                R.drawable.chapter_7_third_chat_3
            ),
            bodyPart4Res = R.string.chapter_seven_body_4
        ),
        StoryChapter(
            id = 8,
            titleRes = R.string.chapter_eight_title,
            bodyPart1Res = R.string.chapter_eight_body_1,
            imageResIds = listOf(
                R.drawable.insta_1,
                R.drawable.insta_2,
                R.drawable.insta_3,
                R.drawable.insta_4,
                R.drawable.insta_5,
                R.drawable.insta_6,
                R.drawable.insta_7,
                R.drawable.insta_8,
                R.drawable.insta_9,
                R.drawable.insta_10,
                R.drawable.insta_11,
                R.drawable.insta_12,
                R.drawable.insta_13,
                R.drawable.insta_14,
                R.drawable.insta_15,
                R.drawable.insta_16,
                R.drawable.insta_17,
                R.drawable.insta_18
            ),
            bodyPart2Res = R.string.chapter_eight_body_2
        ),
        StoryChapter(
            id = 9,
            titleRes = R.string.chapter_nine_title,
            bodyRes = R.string.chapter_nine_body
        ),
        StoryChapter(
            id = 10,
            titleRes = R.string.chapter_ten_title,
            bodyRes = R.string.chapter_ten_body
        ),
        StoryChapter(
            id = 11,
            titleRes = R.string.chapter_eleven_title,
            bodyRes = R.string.chapter_eleven_body
        ),
        StoryChapter(
            id = 12,
            titleRes = R.string.chapter_twelve_title,
            bodyRes = R.string.chapter_twelve_body
        ),
        StoryChapter(
            id = 13,
            titleRes = R.string.chapter_thirteen_title,
            bodyRes = R.string.chapter_thirteen_body
        ),
        StoryChapter(
            id = 14,
            titleRes = R.string.chapter_fourteen_title,
            bodyRes = R.string.chapter_fourteen_body
        ),
        StoryChapter(
            id = 15,
            titleRes = R.string.chapter_fifteen_title,
            bodyRes = R.string.chapter_fifteen_body
        )
    )

    fun chapterById(id: Int): StoryChapter? = chapters.firstOrNull { it.id == id }
}
