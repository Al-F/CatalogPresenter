package com.example.imageloader.ui.model

data class CategoryUi(
    val id: Int,
    val name: String,
    val description: String,
    val products: List<CatalogItemUi>
)