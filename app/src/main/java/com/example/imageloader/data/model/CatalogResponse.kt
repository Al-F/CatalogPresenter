package com.example.imageloader.data.model

import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("products") val products : List<Product>
)
