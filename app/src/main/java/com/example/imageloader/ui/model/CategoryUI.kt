package com.example.imageloader.ui.model

data class CategoryUI(
    val id : Int,
    val name : String,
    val description : String,
    val products : List<CatalogItemUI>
)