package com.example.recipecomposeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.recipecomposeapp.ui.details.RecipeDetailsScreen
import com.example.recipecomposeapp.ui.favorites.FavoritesScreen
import com.example.recipecomposeapp.ui.recipes.RecipesScreen
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel

@Composable
fun RecipesApp() {
    val navController = rememberNavController()

    // Получаем текущий маршрут для управления видимостью нижней панели
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    RecipesAppTheme {
        Scaffold(
            bottomBar = {
                // Показываем нижнюю навигацию только на главных экранах (Категории/Избранное)
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
                        onRecipeClick = { recipeId, recipeObject ->
                            // СОХРАНЯЕМ ОБЪЕКТ В savedStateHandle текущего экрана
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                KEY_RECIPE_OBJECT,
                                recipeObject
                            )
                            // Переходим на экран деталей
                            navController.navigate(Destination.RecipeDetails.createRoute(recipeId))
                        }
                    )
                }

                // 4. Экран Деталей рецепта
                composable(
                    route = Destination.RecipeDetails.route,
                    arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                ) { backStackEntry ->
                    // ДОСТАЕМ ОБЪЕКТ ИЗ savedStateHandle предыдущего экрана
                    val recipe = navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<RecipeUiModel>(KEY_RECIPE_OBJECT)

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