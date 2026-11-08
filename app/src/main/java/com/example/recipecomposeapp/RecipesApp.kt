package com.example.recipecomposeapp

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipecomposeapp.core.ui.navigation.BottomNavigation
import com.example.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.example.recipecomposeapp.data.repository.getRecipesByCategoryId
import com.example.recipecomposeapp.ui.categories.CategoriesScreen
import com.example.recipecomposeapp.ui.details.RecipeDetailsScreen
import com.example.recipecomposeapp.ui.favorites.FavoritesScreen
import com.example.recipecomposeapp.ui.recipes.RecipesScreen
import com.example.recipecomposeapp.ui.recipes.model.toUiModel

@Composable
fun RecipesApp(deepLinkIntent: Intent? = null) {
    val navController = rememberNavController()

    // Получаем текущий маршрут для управления видимостью нижней панели
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // Обработка Deep Link (строгая проверка)
    LaunchedEffect(deepLinkIntent) {
        val data = deepLinkIntent?.data
        if (data != null) {
            val scheme = data.scheme
            val host = data.host
            val pathSegments = data.pathSegments

            var isValidDeepLink = false
            var extractedRecipeId: Int? = null

            // 1. Проверка для custom scheme (recipeapp://recipe/1)
            if (scheme == DEEP_LINK_SCHEME && host == "recipe") {
                if (pathSegments.size == 1) {
                    extractedRecipeId = pathSegments[0].toIntOrNull()
                    isValidDeepLink = extractedRecipeId != null
                }
            }
            // 2. Проверка для HTTPS (https://recipes.aisprints.ru/recipe/1)
            else if (scheme == "https" && host == "recipes.aisprints.ru") {
                if (pathSegments.size == 2 && pathSegments[0] == "recipe") {
                    extractedRecipeId = pathSegments[1].toIntOrNull()
                    isValidDeepLink = extractedRecipeId != null
                }
            }

            // Если ссылка валидна — переходим
            if (isValidDeepLink && extractedRecipeId != null) {
                // launchSingleTop защищает от дублирования экрана, если ссылка пришла дважды
                navController.navigate(Destination.RecipeDetails.createRoute(extractedRecipeId)) {
                    launchSingleTop = true
                }
            }
        }
    }

    RecipesAppTheme {
        Scaffold(
            bottomBar = {
                // Показываем нижнюю навигацию только на главных экранах
                if (currentRoute == Destination.Categories.route || currentRoute == Destination.Favorites.route) {
                    BottomNavigation(
                        onCategoriesClick = {
                            navController.navigate(Destination.Categories.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onFavoriteClick = {
                            navController.navigate(Destination.Favorites.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onRecipesClick = {
                            // Временная кнопка. Открывает рецепты категории 0
                            navController.navigate(Destination.Recipes.createRoute(0, "Бургеры"))
                        }
                    )
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Destination.Categories.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                // 1. Экран Категорий
                composable(Destination.Categories.route) {
                    CategoriesScreen(
                        onCategoryClick = { id, title ->
                            navController.navigate(Destination.Recipes.createRoute(id, title))
                        }
                    )
                }

                // 2. Экран Избранного
                composable(Destination.Favorites.route) {
                    FavoritesScreen()
                }

                // 3. Экран Списка рецептов
                composable(
                    route = Destination.Recipes.route,
                    arguments = listOf(
                        navArgument("categoryId") { type = NavType.IntType },
                        navArgument("categoryTitle") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
                    val categoryTitle = backStackEntry.arguments?.getString("categoryTitle") ?: ""

                    RecipesScreen(
                        categoryId = categoryId,
                        categoryTitle = categoryTitle,
                        onRecipeClick = { recipeId, _ ->
                            navController.navigate(Destination.RecipeDetails.createRoute(recipeId))
                        }
                    )
                }

                // 4. Экран Деталей рецепта
                composable(
                    route = Destination.RecipeDetails.route,
                    arguments = listOf(navArgument(PARAM_RECIPE_ID) { type = NavType.IntType })
                ) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getInt(PARAM_RECIPE_ID) ?: 0

                    // Загружаем рецепт по ID из репозитория (пока ищем в категории Бургеры)
                    val recipe = getRecipesByCategoryId(0).map { it.toUiModel() }.find { it.id == recipeId }

                    if (recipe != null) {
                        RecipeDetailsScreen(recipe = recipe)
                    } else {
                        Text("Ошибка: Рецепт не найден")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesAppPreview() {
    RecipesApp()
}