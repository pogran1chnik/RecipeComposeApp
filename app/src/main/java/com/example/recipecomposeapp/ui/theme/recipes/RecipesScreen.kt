package com.example.recipecomposeapp.ui.recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.components.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimens

@Composable
fun RecipesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Используем ту же обложку, что и для категорий (или можете добавить свою)
        ScreenHeader(
            imagePainter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Обложка рецептов",
            title = "Рецепты"
        )

        // Временный текст
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.PaddingLarge),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Скоро здесь будет список рецептов",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}