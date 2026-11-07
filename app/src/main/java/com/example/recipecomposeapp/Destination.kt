package com.example.recipecomposeapp

sealed class Destination(val route: String) {
    data object Categories : Destination("categories")
    data object Favorites : Destination("favorites")

    data object Recipes : Destination("recipes/{categoryId}/{categoryTitle}") {
        fun createRoute(categoryId: Int, categoryTitle: String): String {
            return "recipes/$categoryId/$categoryTitle"
        }
    }

    // Новый экран деталей
    data object RecipeDetails : Destination("recipe/{recipeId}") {
        fun createRoute(recipeId: Int): String {
            return "recipe/$recipeId"
        }
    }
}

// Ключ для передачи сложного объекта (RecipeUiModel) через savedStateHandle
const val KEY_RECIPE_OBJECT = "recipe_object"