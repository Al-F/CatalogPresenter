package com.example.imageloader.mapper

import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.data.model.Product
import com.example.imageloader.ui.model.CatalogItemUI
import com.example.imageloader.ui.model.CategoryUI

internal object CatalogItemMapper {
    fun mapToCategoriesUi(categories: List<CatalogResponse>?): List<CategoryUI> {
        if (categories.isNullOrEmpty()) {
            return emptyList()
        } else {
            val list = mutableListOf<CategoryUI>()
            categories.forEach { category ->
                val listOfProducts = mutableListOf<CatalogItemUI>()
                category.products.forEach { product ->
                    listOfProducts.add(product.mapToCatalogItemUi())
                }
                list.add(
                    CategoryUI(
                        id = category.id,
                        name = category.name,
                        description = category.description,
                        products = listOfProducts
                    )
                )
            }
            return list
        }
    }

    fun mapToCatalogItemsUi(categories: List<CatalogResponse>?): List<CatalogItemUI> {
        return if (categories.isNullOrEmpty()) {
            emptyList()
        } else {
            val list = mutableListOf<CatalogItemUI>()
            categories.forEach { category ->
                category.products.forEach { product ->
                    list.add(product.mapToCatalogItemUi())
                }
            }
            list
        }
    }

    private fun Product.mapToCatalogItemUi(): CatalogItemUI {
        return CatalogItemUI(
            id = this.id,
            categoryId = this.categoryId,
            name = this.name,
            description = this.description,
            url = this.url
        )
    }
}
