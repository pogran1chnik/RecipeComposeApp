package com.example.recipecomposeapp.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipecomposeapp.core.ui.theme.Dimens
import com.example.recipecomposeapp.ui.recipes.model.IngredientUiModel

@Composable
fun IngredientItem(
    ingredient: IngredientUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.PaddingMedium)
    ) {
        // Название ингредиента (заглавными)
        Text(
            text = ingredient.name.uppercase(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        // Количество и единицы измерения (заглавными)
        Text(
            text = "${ingredient.quantity} ${ingredient.unitOfMeasure}".uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}