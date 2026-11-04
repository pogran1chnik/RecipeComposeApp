package com.example.recipecomposeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.core.ui.theme.Dimens

/**
 * Переиспользуемый компонент заголовка экрана.
 *
 * @param imagePainter Изображение (обложка) для фона.
 * @param contentDescription Описание изображения для доступности (TalkBack).
 * @param title Текст заголовка, который будет выведен заглавными буквами.
 */
@Composable
fun ScreenHeader(
    imagePainter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    // Корневой элемент — Box заданной высоты
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.HeaderHeight)
    ) {
        // 1. Изображение на весь Box
        Image(
            painter = imagePainter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 2. Surface с заголовком в левом нижнем углу
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart) // Доступно только внутри BoxScope
                .padding(Dimens.PaddingMain),
            shape = RoundedCornerShape(Dimens.CardCornerRadius),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f) // Полупрозрачный белый фон
        ) {
            Text(
                text = title.uppercase(), // Заглавные буквы
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    horizontal = Dimens.PaddingMediumLarge,
                    vertical = Dimens.PaddingMedium
                )
            )
        }
    }
}