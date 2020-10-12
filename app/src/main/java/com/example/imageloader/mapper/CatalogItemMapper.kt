package com.example.imageloader.mapper

import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.ui.model.CatalogItemUI

internal object CatalogItemMapper {

    fun mapToCatalogItemsUi(categories: List<CatalogResponse>): List<CatalogItemUI> {
        val list = mutableListOf<CatalogItemUI>()
        categories.forEach { categorie ->
            val id = categorie.id
            val name = categorie.name
            val description = categorie.description
            categorie.products.forEach {
                list.add(
                    CatalogItemUI(
                        id = it.id,
                        categoryId = it.categoryId,
                        name = it.name,
                        description = it.description,
                        url = it.url
                    )
                )
            }
        }
        return list
    }
}
