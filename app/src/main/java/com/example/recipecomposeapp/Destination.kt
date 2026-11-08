package com.example.recipecomposeapp

sealed class Destination(val route: String) {
    data object Categories : Destination("categories")
    data object Favorites : Destination("favorites")

    data object Recipes : Destination("recipes/{categoryId}/{categoryTitle}") {
        fun createRoute(categoryId: Int, categoryTitle: String): String {
            return "recipes/$categoryId/$categoryTitle"
        }
    }

    // Обновляем маршрут деталей
    data object RecipeDetails : Destination("recipe/{$PARAM_RECIPE_ID}") {
        fun createRoute(recipeId: Int): String {
            return "recipe/$recipeId"
        }
    }
}