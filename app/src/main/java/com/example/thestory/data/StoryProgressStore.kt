package com.example.thestory.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Remembers which chapters have been read, so opened chapters
 * glow with a filled heart on the shelf.
 */
private val Context.storyDataStore by preferencesDataStore(name = "story_progress")

object StoryProgressStore {

    private val READ_CHAPTERS_KEY = stringSetPreferencesKey("read_chapters")

    fun readChaptersFlow(context: Context): Flow<Set<Int>> =
        context.storyDataStore.data.map { preferences ->
            preferences[READ_CHAPTERS_KEY]
                ?.mapNotNull { it.toIntOrNull() }
                ?.toSet()
                ?: emptySet()
        }

    suspend fun markRead(context: Context, chapterId: Int) {
        context.storyDataStore.edit { preferences ->
            val current = preferences[READ_CHAPTERS_KEY] ?: emptySet()
            preferences[READ_CHAPTERS_KEY] = current + chapterId.toString()
        }
    }
}
