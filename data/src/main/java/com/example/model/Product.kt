package com.example.model

import com.google.gson.annotations.SerializedName

class Product(
    @SerializedName("id") val id : Int,
    @SerializedName("categoryId") val categoryId : Int,
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String,
    @SerializedName("description") val description : String,
    //@SerializedName("salePrice") val salePrice : SalePrice
)