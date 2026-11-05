package com.example.recipecomposeapp.ui.recipes.model

import androidx.compose.runtime.Immutable
import com.example.recipecomposeapp.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.data.model.RecipeDto

@Immutable
data class RecipeUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val ingredients: List<IngredientUiModel>,
    val method: List<String>,
    val isFavorite: Boolean = false // По умолчанию false
)

/**
 * Функция-расширение для маппинга DTO в UI-модель.
 */
fun RecipeDto.toUiModel(): RecipeUiModel {
    // Проверяем URL картинки: если это не http-ссылка, значит это локальный файл
    val formattedImageUrl = if (imageUrl.startsWith("http")) {
        imageUrl
    } else {
        "$ASSETS_URI_PREFIX$imageUrl"
    }

    return RecipeUiModel(
        id = id,
        title = title,
        imageUrl = formattedImageUrl,
        // Преобразуем каждый IngredientDto в IngredientUiModel
        ingredients = this.ingredients.map { it.toUiModel() },
        method = this.method,
        isFavorite = false
    )
}