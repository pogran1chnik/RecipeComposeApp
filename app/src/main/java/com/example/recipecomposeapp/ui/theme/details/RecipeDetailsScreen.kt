package com.example.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.core.ui.components.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimens
import com.example.recipecomposeapp.data.repository.getRecipesByCategoryId
import com.example.recipecomposeapp.ui.details.components.IngredientItem
import com.example.recipecomposeapp.ui.details.components.PortionsSelector
import com.example.recipecomposeapp.ui.recipes.model.toUiModel
import coil3.compose.AsyncImage

@Composable
fun RecipeDetailsScreen(
    recipeId: Int,
    modifier: Modifier = Modifier
) {
    // Временно загружаем рецепты из категории бургеров (0),
    // так как у нас пока нет метода getRecipeById в репозитории.
    val recipe = remember(recipeId) {
        getRecipesByCategoryId(0).map { it.toUiModel() }.find { it.id == recipeId }
    }

    if (recipe == null) {
        Text("Рецепт не найден", modifier = modifier.padding(Dimens.PaddingLarge))
        return
    }

    var portions by remember { mutableIntStateOf(recipe.basePortions) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // 1. Шапка с названием и картинкой (компонент из core.ui)
        ScreenHeader(
            imagePainter = coil3.compose.rememberAsyncImagePainter(recipe.imageUrl),
            contentDescription = recipe.title,
            title = recipe.title
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

            // Пересчитываем количество ингредиентов в зависимости от порций
            val adjustedIngredients = remember(portions, recipe.ingredients, recipe.basePortions) {
                recipe.ingredients.map { ingredient ->
                    val originalQuantity = ingredient.quantity.toDoubleOrNull()
                    if (originalQuantity != null) {
                        // Считаем пропорционально: (исходное * выбранное) / базовое
                        val newQuantity = (originalQuantity * portions) / recipe.basePortions

                        // Форматируем обратно в строку (убираем лишние нули после запятой)
                        val formattedQuantity = if (newQuantity % 1.0 == 0.0) {
                            newQuantity.toInt().toString()
                        } else {
                            // Округляем до 2 знаков после запятой, если есть дробная часть
                            String.format("%.2f", newQuantity).replace(",", ".").trimEnd('0').trimEnd('.')
                        }
                        ingredient.copy(quantity = formattedQuantity)
                    } else {
                        ingredient // "по вкусу" оставляем без изменений
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
                    text = "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = Dimens.PaddingMedium)
                )
            }
        }
    }
}