package com.example.recipecomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.recipecomposeapp.ui.categories.CategoriesScreen
import com.example.recipecomposeapp.ui.favorites.FavoritesScreen
import com.example.recipecomposeapp.ui.recipes.RecipesScreen

@Composable
fun RecipesApp() {
    // Создаем контроллер навигации
    val navController = rememberNavController()

    // Получаем текущий маршрут, чтобы скрывать/показывать нижнюю панель
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

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
                            // Временная кнопка "Рецепты" в нижнем меню.
                            // Пока открывает рецепты категории 0 (Бургеры)
                            navController.navigate(Destination.Recipes.createRoute(0, "Бургеры"))
                        }
                    )
                }
            }
        ) { paddingValues ->
            // Навигационный граф
            NavHost(
                navController = navController,
                startDestination = Destination.Categories.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                // 1. Экран Категорий
                composable(Destination.Categories.route) {
                    CategoriesScreen(
                        onCategoryClick = { id, title ->
                            // Переходим на экран рецептов, передавая параметры в URL
                            navController.navigate(Destination.Recipes.createRoute(id, title))
                        }
                    )
                }

                // 2. Экран Избранного
                composable(Destination.Favorites.route) {
                    FavoritesScreen()
                }

                // 3. Экран Рецептов (с параметрами)
                composable(
                    route = Destination.Recipes.route,
                    arguments = listOf(
                        navArgument("categoryId") { type = NavType.IntType },
                        navArgument("categoryTitle") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    // Достаем параметры из URL
                    val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
                    val categoryTitle = backStackEntry.arguments?.getString("categoryTitle") ?: ""

                    RecipesScreen(
                        categoryId = categoryId,
                        categoryTitle = categoryTitle,
                        onRecipeClick = { recipeId ->
                            println("Выбран рецепт с ID: $recipeId")
                        }
                    )
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