package com.example.recipecomposeapp.ui.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun FavoritesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Шапка с фоновым изображением избранного
        ScreenHeader(
            imagePainter = painterResource(id = R.drawable.bcg_favorites),
            contentDescription = "Обложка избранного",
            title = "Избранное"
        )

        // Заглушка для будущего списка
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.PaddingLarge),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Здесь скоро появятся ваши любимые рецепты",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}