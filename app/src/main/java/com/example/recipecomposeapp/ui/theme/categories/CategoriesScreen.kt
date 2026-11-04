package com.example.recipecomposeapp.ui.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.theme.Dimens
import com.example.recipecomposeapp.ui.components.ScreenHeader

@Composable
fun CategoriesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        // 1. Красивая шапка с изображением


        // 2. Заглушка для будущего списка категорий
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.PaddingLarge),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Здесь скоро появится список категорий",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}