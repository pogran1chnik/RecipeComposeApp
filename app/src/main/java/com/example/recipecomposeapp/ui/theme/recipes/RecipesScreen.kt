package com.example.recipecomposeapp.ui.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.components.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimens
import com.example.recipecomposeapp.data.repository.getRecipesByCategoryId
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.example.recipecomposeapp.ui.recipes.model.toUiModel
import androidx.compose.foundation.layout.wrapContentSize

@Composable
fun RecipesScreen(
    categoryId: Int,
    categoryTitle: String,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Состояние для хранения списка рецептов
    var recipes by remember { mutableStateOf<List<RecipeUiModel>>(emptyList()) }

    // LaunchedEffect сработает при первом запуске и каждый раз, когда изменится categoryId
    LaunchedEffect(categoryId) {
        // Имитация загрузки из репозитория и маппинг в UI-модели
        recipes = getRecipesByCategoryId(categoryId).map { it.toUiModel() }
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Шапка
        ScreenHeader(
            imagePainter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Обложка рецептов",
            title = categoryTitle // Заголовок берем из названия категории
        )

        if (recipes.isEmpty()) {
            // Заглушка, если рецептов нет
            Text(
                text = "Скоро здесь будет список рецептов",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.PaddingLarge)
                    .wrapContentSize(Alignment.Center) // Центрируем текст
            )
        } else {
            // Список рецептов
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(Dimens.PaddingMain),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
            ) {
                items(recipes, key = { it.id }) { recipe ->
                    RecipeItem(
                        recipe = recipe,
                        onClick = onRecipeClick
                    )
                }
            }
        }
    }
}