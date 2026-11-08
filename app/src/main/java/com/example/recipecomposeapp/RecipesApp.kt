package com.example.recipecomposeapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
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
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // Обработка Deep Link
    LaunchedEffect(deepLinkIntent) {
        val data: Uri? = deepLinkIntent?.data
        if (data != null) {
            // Пример: recipeapp://recipe/1 или https://recipes.aisprints.ru/recipe/5
            // lastPathSegment вернет "1" или "5"
            val recipeId = data.lastPathSegment?.toIntOrNull()
            if (recipeId != null) {
                navController.navigate(Destination.RecipeDetails.createRoute(recipeId))
            }
        }
    }

    RecipesAppTheme {
        Scaffold(
            bottomBar = {
                if (currentRoute == Destination.Categories.route || currentRoute == Destination.Favorites.route) {
                    BottomNavigation(
                        onCategoriesClick = { navController.navigate(Destination.Categories.route) { popUpTo(navController.graph.startDestinationId) { saveState = true }; launchSingleTop = true } },
                        onFavoriteClick = { navController.navigate(Destination.Favorites.route) { popUpTo(navController.graph.startDestinationId) { saveState = true }; launchSingleTop = true } },
                        onRecipesClick = { navController.navigate(Destination.Recipes.createRoute(0, "Бургеры")) }
                    )
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Destination.Categories.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Destination.Categories.route) {
                    CategoriesScreen(onCategoryClick = { id, title -> navController.navigate(Destination.Recipes.createRoute(id, title)) })
                }

                composable(Destination.Favorites.route) {
                    FavoritesScreen()
                }

                composable(
                    route = Destination.Recipes.route,
                    arguments = listOf(navArgument("categoryId") { type = NavType.IntType }, navArgument("categoryTitle") { type = NavType.StringType })
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

                // Экран Деталей
                composable(
                    route = Destination.RecipeDetails.route,
                    arguments = listOf(navArgument(PARAM_RECIPE_ID) { type = NavType.IntType })
                ) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getInt(PARAM_RECIPE_ID) ?: 0

                    // Загружаем рецепт из репозитория по ID
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