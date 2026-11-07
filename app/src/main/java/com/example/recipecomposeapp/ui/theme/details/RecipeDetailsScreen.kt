package com.example.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recipecomposeapp.core.ui.theme.Dimens
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel

@Composable
fun RecipeDetailsScreen(
    recipe: RecipeUiModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.PaddingMain),
        contentAlignment = Alignment.Center
    ) {
        // Пока просто выводим название для проверки
        Text(
            text = "Детали рецепта:\n${recipe.title}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}