package com.example.recipecomposeapp.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.recipecomposeapp.core.ui.theme.Dimens

@Composable
fun PortionsSelector(
    portions: Int,
    onPortionsChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.PaddingMedium)
    ) {
        // Текущее количество порций над слайдером
        Text(
            text = "$portions ПОРЦИЙ".uppercase(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Слайдер для выбора порций (от 1 до 10)
        Slider(
            value = portions.toFloat(),
            onValueChange = { onPortionsChange(it.toInt()) },
            valueRange = 1f..10f,
            steps = 8, // 10 - 1 - 1 = 8 промежуточных шагов
            modifier = Modifier.padding(top = Dimens.PaddingMedium)
        )
    }
}