package com.example.recipecomposeapp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ==================== СВЕТЛАЯ СХЕМА ====================
private val RecipesAppLightColorScheme = lightColorScheme(
    // Основные роли
    primary = PrimaryColor,
    onPrimary = Color.White,

    secondary = AccentBlue,
    onSecondary = Color.White,

    tertiary = AccentColor,
    onTertiary = Color.White,

    // Ошибки
    error = AccentColor,
    onError = Color.White,

    // Фоны
    background = BackgroundColor,
    onBackground = TextPrimaryColor,

    surface = SurfaceColor,
    onSurface = TextPrimaryColor,

    surfaceVariant = SurfaceVariantColor,
    onSurfaceVariant = TextSecondaryColor,

    // Разделители
    outline = DividerColor,
    outlineVariant = DividerColor
)

// ==================== ТЁМНАЯ СХЕМА ====================
private val RecipesAppDarkColorScheme = darkColorScheme(
    // Основные роли
    primary = PrimaryColorDark,
    onPrimary = Color.Black,

    secondary = AccentBlueDark,
    onSecondary = Color.Black,

    tertiary = AccentColorDark,
    onTertiary = Color.Black,

    // Ошибки
    error = AccentColorDark,
    onError = Color.Black,

    // Фоны
    background = BackgroundColorDark,
    onBackground = TextPrimaryColorDark,

    surface = SurfaceColorDark,
    onSurface = TextPrimaryColorDark,

    surfaceVariant = SurfaceVariantColorDark,
    onSurfaceVariant = TextSecondaryColorDark,

    // Разделители
    outline = DividerColorDark,
    outlineVariant = DividerColorDark
)

// ==================== ТЕМА ПРИЛОЖЕНИЯ ====================
@Composable
fun RecipesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        RecipesAppDarkColorScheme
    } else {
        RecipesAppLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = com.example.recipecomposeapp.ui.theme.recipesAppTypography,
        content = content
    )
}
