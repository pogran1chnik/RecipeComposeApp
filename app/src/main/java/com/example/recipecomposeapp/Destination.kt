package com.example.recipecomposeapp

sealed class Destination(val route: String) {
    // Главные экраны (без параметров)
    data object Categories : Destination("categories")
    data object Favorites : Destination("favorites")

    // Экран списка рецептов (принимает ID и название категории)
    data object Recipes : Destination("recipes/{categoryId}/{categoryTitle}") {
        // Функция для создания правильного URL при навигации
        fun createRoute(categoryId: Int, categoryTitle: String): String {
            return "recipes/$categoryId/$categoryTitle"
        }
    }
}