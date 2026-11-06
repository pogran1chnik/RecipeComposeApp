package com.example.recipecomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
    // 1. Состояние текущего экрана
    var currentScreen by remember { mutableStateOf(ScreenId.CATEGORIES) }

    // 2. Новые состояния для выбранной категории
    var selectedCategoryId by remember { mutableIntStateOf(0) }
    var selectedCategoryTitle by remember { mutableStateOf("") }

    RecipesAppTheme {
        Scaffold(
            bottomBar = {
                // Если мы не на экране категорий, можно скрывать нижнюю навигацию (опционально)
                if (currentScreen != ScreenId.RECIPES) {
                    BottomNavigation(
                        onCategoriesClick = { currentScreen = ScreenId.CATEGORIES },
                        onFavoriteClick = { currentScreen = ScreenId.FAVORITES },
                        onRecipesClick = { currentScreen = ScreenId.RECIPES }
                    )
                }
            }
        ) { paddingValues ->
            when (currentScreen) {
                ScreenId.CATEGORIES -> CategoriesScreen(
                    modifier = Modifier.padding(paddingValues),
                    onCategoryClick = { id, title ->
                        // Сохраняем данные выбранной категории
                        selectedCategoryId = id
                        selectedCategoryTitle = title
                        // Переходим на экран рецептов
                        currentScreen = ScreenId.RECIPES
                    }
                )
                ScreenId.FAVORITES -> FavoritesScreen(
                    modifier = Modifier.padding(paddingValues)
                )
                ScreenId.RECIPES -> RecipesScreen(
                    categoryId = selectedCategoryId,
                    categoryTitle = selectedCategoryTitle,
                    onRecipeClick = { recipeId ->
                        // Здесь в будущем будет переход на детальный экран рецепта
                        println("Выбран рецепт с ID: $recipeId")
                    },
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