package com.example.recipecomposeapp.data.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("ingredients")
    val ingredients: List<IngredientDto>,

    @SerializedName("method")
    val method: List<String>,

    @SerializedName("imageUrl")
    val imageUrl: String
)