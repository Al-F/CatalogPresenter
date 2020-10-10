package com.example.model

import com.google.gson.annotations.SerializedName

class ImageServiceResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("products") val products : List<Product>
)
