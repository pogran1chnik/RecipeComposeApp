package com.example.recipecomposeapp.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.components.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimens
import com.example.recipecomposeapp.data.repository.getCategories
import com.example.recipecomposeapp.ui.categories.model.toUiModel

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (Int, String)  -> Unit
) {
    // Загружаем данные из заглушки и мапим в UI-модели (через remember, чтобы не пересчитывать при рекомпозиции)
    val categories = remember { getCategories().map { it.toUiModel() } }

    Column(modifier = modifier.fillMaxSize()) {
        // Шапка (вне сетки)
        ScreenHeader(
            imagePainter = painterResource(id = R.drawable.ic_launcher_foreground), // Замените на свою обложку
            contentDescription = "Обложка категорий",
            title = "Категории"
        )

        // Сетка карточек
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Две колонки
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(Dimens.PaddingMain), // Отступы от краев экрана
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium), // Отступ между колонками
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium) // Отступ между рядами
        ) {
            items(categories, key = { it.id }) { category ->
                CategoryItem(
                    category = category,
                    // Передаем и ID, и Title
                    onClick = { onCategoryClick(category.id, category.title) }
                )
            }
        }
    }
}