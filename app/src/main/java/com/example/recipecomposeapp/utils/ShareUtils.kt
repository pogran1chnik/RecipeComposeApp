package com.example.recipecomposeapp.utils

import android.content.Context
import android.content.Intent
import com.example.recipecomposeapp.createRecipeDeepLink

fun shareRecipe(context: Context, recipeId: Int, recipeTitle: String) {
    val deepLink = createRecipeDeepLink(recipeId)
    val shareText = "Посмотри этот рецепт: $recipeTitle\n\n$deepLink"

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Поделиться рецептом")
    context.startActivity(shareIntent)
}