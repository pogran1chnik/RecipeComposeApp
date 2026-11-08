package com.example.recipecomposeapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.example.recipecomposeapp.core.ui.theme.Dimens

@Composable
fun ScreenHeader(
    imagePainter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
    showShareButton: Boolean = false, // Новый параметр (по умолчанию false)
    onShareClick: () -> Unit = {}     // Новый параметр (пустая лямбда по умолчанию)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.HeaderHeight)
    ) {
        // 1. Фоновое изображение
        Image(
            painter = imagePainter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 2. Кнопка "Поделиться" (если showShareButton = true)
        if (showShareButton) {
            IconButton(
                onClick = onShareClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(Dimens.PaddingMain)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Поделиться",
                    // Используем белый цвет (или onPrimary), чтобы иконка была видна на фото
                    tint = Color.White
                )
            }
        }

        // 3. Плашка с заголовком
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Dimens.PaddingMain),
            shape = RoundedCornerShape(Dimens.CardCornerRadius),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
        ) {
            Text(
                text = title.uppercase(),
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