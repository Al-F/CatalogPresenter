package com.example.model

import com.google.gson.annotations.SerializedName

class SalePrice (
    @SerializedName("amount") val amount : String,
    @SerializedName("currency") val currency : String
)
