package com.example.thestory.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font as GoogleFontsFont
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.thestory.R

/**
 * Storybook typography: Quicksand everywhere — a rounded, warm Google Font
 * with proper upper- and lowercase letters and real weight variations,
 * keeping both the titles playful and the body comfortable to read.
 *
 * (Previously the titles used Gagalin, but it is an all-caps font with a
 * single weight, so it was replaced. The old gagalin_regular.otf is still
 * in res/font/ and can be deleted if it is no longer wanted.)
 */

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val quicksandFont = GoogleFont("Quicksand")

val Quicksand = FontFamily(
    GoogleFontsFont(quicksandFont, provider, weight = FontWeight.Normal),
    GoogleFontsFont(quicksandFont, provider, weight = FontWeight.Medium),
    GoogleFontsFont(quicksandFont, provider, weight = FontWeight.SemiBold),
    GoogleFontsFont(quicksandFont, provider, weight = FontWeight.Bold)
)

private val bold = FontWeight.Bold
private val semiBold = FontWeight.SemiBold

val StoryTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Quicksand,
        fontWeight = bold,
        fontSize = 44.sp,
        lineHeight = 52.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        shadow = Shadow(
            color = StoryInk,
            offset = Offset(1.5f, 1.5f),
            blurRadius = 0f
        ),
        fontSize = 30.sp,
        lineHeight = 40.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Quicksand,
        fontWeight = bold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        fontSize = 18.sp,
        lineHeight = 33.sp,
        letterSpacing = 0.3.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        fontSize = 16.sp,
        lineHeight = 25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        fontSize = 16.sp,
        letterSpacing = 1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 1.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Quicksand,
        fontWeight = semiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.2.sp
    )
)