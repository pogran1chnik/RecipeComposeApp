package com.example.recipecomposeapp.ui.categories.model

import androidx.compose.runtime.Immutable
import com.example.recipecomposeapp.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.data.model.CategoryDto

@Immutable
data class CategoryUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)

/**
 * Функция-расширение для маппинга DTO в UI-модель.
 */
fun CategoryDto.toUiModel(): CategoryUiModel {
    // По заданию: "Преобразовать name → title (если в DTO поле называется name)"
    // В нашем CategoryDto поле уже называется title, поэтому просто используем его.
    // Если бы оно называлось name, мы бы написали title = this.name.

    // Проверяем URL картинки: если это не http-ссылка, значит это локальный файл в assets
    val formattedImageUrl = if (imageUrl.startsWith("http")) {
        imageUrl
    } else {
        "$ASSETS_URI_PREFIX$imageUrl"
    }

    return CategoryUiModel(
        id = id,
        title = title,
        description = description,
        imageUrl = formattedImageUrl
    )
}