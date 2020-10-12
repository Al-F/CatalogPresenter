package com.example.imageloader.ui.model

data class CatalogItemUI(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val url: String,
    val description: String,
    val salePrice: SalePriceUI
)
