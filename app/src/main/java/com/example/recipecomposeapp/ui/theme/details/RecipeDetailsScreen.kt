package com.example.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.compose.rememberAsyncImagePainter
import com.example.recipecomposeapp.core.ui.components.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimens
import com.example.recipecomposeapp.ui.details.components.IngredientItem
import com.example.recipecomposeapp.ui.details.components.PortionsSelector
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.example.recipecomposeapp.utils.shareRecipe

@Composable
fun RecipeDetailsScreen(
    recipe: RecipeUiModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Инициализируем слайдер базовым количеством порций рецепта
    var portions by remember { mutableIntStateOf(recipe.basePortions) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // 1. Шапка с кнопкой "Поделиться"
        ScreenHeader(
            imagePainter = rememberAsyncImagePainter(recipe.imageUrl),
            contentDescription = recipe.title,
            title = recipe.title,
            showShareButton = true, // Включаем кнопку
            onShareClick = {
                // Вызываем утилиту для шеринга
                shareRecipe(context, recipe.id, recipe.title)
            }
        )

        Column(modifier = Modifier.padding(Dimens.PaddingMain)) {
            // 2. Секция выбора порций
            PortionsSelector(
                portions = portions,
                onPortionsChange = { portions = it }
            )

            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            // 3. Секция "Ингредиенты"
            Text(
                text = "ИНГРЕДИЕНТЫ".uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

            // Пересчитываем количество ингредиентов с учетом базового количества порций
            val adjustedIngredients = remember(portions, recipe.ingredients, recipe.basePortions) {
                recipe.ingredients.map { ingredient ->
                    val originalQuantity = ingredient.quantity.toDoubleOrNull()
                    if (originalQuantity != null) {
                        val newQuantity = (originalQuantity * portions) / recipe.basePortions

                        val formattedQuantity = if (newQuantity % 1.0 == 0.0) {
                            newQuantity.toInt().toString()
                        } else {
                            String.format("%.2f", newQuantity).replace(",", ".").trimEnd('0').trimEnd('.')
                        }
                        ingredient.copy(quantity = formattedQuantity)
                    } else {
                        ingredient
                    }
                }
            }

            adjustedIngredients.forEachIndexed { index, ingredient ->
                IngredientItem(ingredient = ingredient)
                if (index < adjustedIngredients.lastIndex) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
            }

            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

            // 4. Секция "Способ приготовления"
            Text(
                text = "СПОСОБ ПРИГОТОВЛЕНИЯ".uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

            recipe.method.forEachIndexed { index, step ->
                Text(
                    text = "${index + 1}. $step", // Нумерация генерируется в UI
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = Dimens.PaddingMedium)
                )
            }
        }
    }
}