package com.example.recipecomposeapp.ui.recipes.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.recipecomposeapp.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.data.model.RecipeDto
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class RecipeUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val ingredients: List<IngredientUiModel>,
    val method: List<String>,
    val isFavorite: Boolean = false
) : Parcelable

fun RecipeDto.toUiModel(): RecipeUiModel {
    val formattedImageUrl = if (imageUrl.startsWith("http")) {
        imageUrl
    } else {
        "$ASSETS_URI_PREFIX$imageUrl"
    }

    return RecipeUiModel(
        id = id,
        title = title,
        imageUrl = formattedImageUrl,
        ingredients = this.ingredients.map { it.toUiModel() },
        method = this.method,
        isFavorite = false
    )
}