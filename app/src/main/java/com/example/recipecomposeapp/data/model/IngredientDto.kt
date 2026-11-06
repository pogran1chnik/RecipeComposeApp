package com.example.recipecomposeapp.data.model

import com.google.gson.annotations.SerializedName

data class IngredientDto(
    @SerializedName("quantity")
    val quantity: String,

    @SerializedName("unitOfMeasure")
    val unitOfMeasure: String,

    @SerializedName("description")
    val description: String
)