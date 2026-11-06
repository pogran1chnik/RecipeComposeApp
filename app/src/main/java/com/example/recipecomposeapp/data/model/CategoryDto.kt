package com.example.recipecomposeapp.data.model

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id", alternate = ["идентификатор"])
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageUrl", alternate = ["ImageUrl", "Ссылка на изображение"])
    val imageUrl: String
)