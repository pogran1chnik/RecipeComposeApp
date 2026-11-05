package com.example.recipecomposeapp.ui.recipes.model

import androidx.compose.runtime.Immutable
import com.example.recipecomposeapp.data.model.IngredientDto

@Immutable
data class IngredientUiModel(
    val name: String,      // В UI-модели поле называется name
    val quantity: String,
    val unitOfMeasure: String
)

/**
 * Функция-расширение для маппинга DTO в UI-модель.
 */
fun IngredientDto.toUiModel(): IngredientUiModel {
    return IngredientUiModel(
        name = this.description, // В DTO поле называется description, а в UI — name
        quantity = this.quantity,
        unitOfMeasure = this.unitOfMeasure
    )
}