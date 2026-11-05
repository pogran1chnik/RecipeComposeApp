package com.example.recipecomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipecomposeapp.core.ui.navigation.BottomNavigation
import com.example.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.example.recipecomposeapp.ui.categories.CategoriesScreen
import com.example.recipecomposeapp.ui.favorites.FavoritesScreen
import com.example.recipecomposeapp.ui.recipes.RecipesScreen

@Composable
fun RecipesApp() {
    // Состояние текущего экрана
    var currentScreen by remember { mutableStateOf(ScreenId.CATEGORIES) }

    RecipesAppTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    onCategoriesClick = { currentScreen = ScreenId.CATEGORIES },
                    onFavoriteClick = { currentScreen = ScreenId.FAVORITES },
                    onRecipesClick = { currentScreen = ScreenId.RECIPES } // Передаем коллбэк
                )
            }
        ) { paddingValues ->
            // Переключение контента в зависимости от текущего экрана
            when (currentScreen) {
                ScreenId.CATEGORIES -> CategoriesScreen(
                    modifier = Modifier.padding(paddingValues)
                )
                ScreenId.FAVORITES -> FavoritesScreen(
                    modifier = Modifier.padding(paddingValues)
                )
                ScreenId.RECIPES -> RecipesScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesAppPreview() {
    RecipesApp()
}