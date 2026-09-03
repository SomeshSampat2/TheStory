package com.example.thestory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.thestory.ui.navigation.StoryNavGraph
import com.example.thestory.ui.theme.TheStoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheStoryTheme {
                StoryNavGraph()
            }
        }
    }
}