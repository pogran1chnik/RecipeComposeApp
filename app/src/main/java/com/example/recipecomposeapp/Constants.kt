package com.example.recipecomposeapp

const val ASSETS_URI_PREFIX = "file:///android_asset/"

// Константа для параметра Deep Link
const val PARAM_RECIPE_ID = "recipeId"

// Базовый URL для HTTPS Deep Link
const val HTTPS_BASE_URL = "https://recipes.aisprints.ru/recipe/"

// Схема для custom Deep Link
const val DEEP_LINK_SCHEME = "recipeapp"

// Функция для формирования ссылки для шеринга
fun createRecipeDeepLink(recipeId: Int): String {
    return "$HTTPS_BASE_URL$recipeId"
}