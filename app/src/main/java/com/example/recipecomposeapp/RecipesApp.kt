package com.example.recipecomposeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipecomposeapp.core.ui.navigation.BottomNavigation
import com.example.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.example.recipecomposeapp.ui.categories.CategoriesScreen // <-- Импорт нового экрана

@Composable
fun RecipesApp() {
    var currentScreen by remember { mutableStateOf(ScreenId.CATEGORIES) }

    RecipesAppTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    onCategoriesClick = { currentScreen = ScreenId.CATEGORIES },
                    onFavoriteClick = { currentScreen = ScreenId.FAVORITES }
                )
            }
        ) { paddingValues ->
            when (currentScreen) {
                ScreenId.CATEGORIES -> CategoriesScreen( // <-- Вызов нового экрана
                    modifier = Modifier.padding(paddingValues)
                )
                ScreenId.FAVORITES -> FavoritesScreen( // Заглушку избранного можно пока оставить здесь
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

// Временная заглушка для Избранного (можно оставить пока в этом файле)
@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Экран Избранного",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesAppPreview() {
    RecipesApp()
}