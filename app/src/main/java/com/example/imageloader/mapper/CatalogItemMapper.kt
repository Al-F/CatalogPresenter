package com.example.imageloader.mapper

import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.data.model.Product
import com.example.imageloader.ui.model.CatalogItemUi
import com.example.imageloader.ui.model.CategoryUi
import com.example.imageloader.ui.model.SalePriceUi

internal object CatalogItemMapper {
    fun mapToCategoriesUi(categories: List<CatalogResponse>?): List<CategoryUi> {
        if (categories.isNullOrEmpty()) {
            return emptyList()
        } else {
            val list = mutableListOf<CategoryUi>()
            categories.forEach { category ->
                val listOfProducts = mutableListOf<CatalogItemUi>()
                category.products.forEach { product ->
                    listOfProducts.add(product.mapToCatalogItemUi())
                }
                list.add(
                    CategoryUi(
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

    fun mapToCatalogItemsUi(categories: List<CatalogResponse>?): List<CatalogItemUi> {
        return if (categories.isNullOrEmpty()) {
            emptyList()
        } else {
            val list = mutableListOf<CatalogItemUi>()
            categories.forEach { category ->
                category.products.forEach { product ->
                    list.add(product.mapToCatalogItemUi())
                }
            }
            list
        }
    }

    private fun Product.mapToCatalogItemUi(): CatalogItemUi {
        return CatalogItemUi(
            id = this.id,
            categoryId = this.categoryId,
            name = this.name,
            description = this.description,
            url = this.url,
            salePrice = SalePriceUi(
                this.salePrice.amount,
                this.salePrice.currency
            )
        )
    }
}
