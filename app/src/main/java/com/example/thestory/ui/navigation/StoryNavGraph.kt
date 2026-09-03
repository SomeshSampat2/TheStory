package com.example.thestory.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thestory.ui.screens.ChapterDetailScreen
import com.example.thestory.ui.screens.ChaptersScreen
import com.example.thestory.ui.screens.SplashScreen

/**
 * The story flows like pages turning: soft fades and gentle slides
 * between the splash, the shelf of chapters, and each page.
 */
@Composable
fun StoryNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = StoryDestinations.SPLASH_ROUTE,
        enterTransition = {
            fadeIn(animationSpec = tween(650)) +
                slideInVertically(animationSpec = tween(650)) { it / 8 }
        },
        exitTransition = {
            fadeOut(animationSpec = tween(450))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(500))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(450)) +
                slideOutVertically(animationSpec = tween(450)) { it / 8 }
        }
    ) {
        composable(route = StoryDestinations.SPLASH_ROUTE) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(StoryDestinations.CHAPTERS_ROUTE) {
                        popUpTo(StoryDestinations.SPLASH_ROUTE) { inclusive = true }
                    }
                }
            )
        }
        composable(route = StoryDestinations.CHAPTERS_ROUTE) {
            ChaptersScreen(
                onChapterSelected = { chapterId ->
                    navController.navigate(StoryDestinations.chapterDetailRoute(chapterId))
                }
            )
        }
        composable(
            route = StoryDestinations.CHAPTER_DETAIL_ROUTE,
            arguments = listOf(
                navArgument(StoryDestinations.ARG_CHAPTER_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val chapterId = backStackEntry.arguments
                ?.getInt(StoryDestinations.ARG_CHAPTER_ID) ?: 1
            ChapterDetailScreen(
                chapterId = chapterId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
