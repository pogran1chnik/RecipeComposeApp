package com.example.recipecomposeapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val recipesAppTypography = Typography(
    // Заголовки экранов — Montserrat Alternates SemiBold
    displayLarge = TextStyle(
        fontFamily = montserratAlternatesFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    // Карточки — Montserrat Alternates SemiBold
    titleMedium = TextStyle(
        fontFamily = montserratAlternatesFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    // Основной текст — Montserrat Regular
    bodyMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    // Мелкий текст — Montserrat Regular
    bodySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    // Кнопки — Montserrat Medium
    labelLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)
@Preview(showBackground = true)
@Composable
fun TypographyPreview() {
    RecipeComposeAppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "displayLarge - Заголовки экранов",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                "titleMedium - Карточки",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                "bodyMedium - Основной текст",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "bodySmall - Мелкий текст",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "labelLarge - Кнопки",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}