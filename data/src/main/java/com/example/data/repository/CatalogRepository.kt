package com.example.data.repository

import com.example.api.CatalogAPI
import com.example.model.ImageServiceResponse
import com.example.model.Product
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogRepository () {
    fun getCatalogItems(): Single<List<CatalogItemUi>> {
        return superServiceApi.getCatalogItems()
            .flatMapPublisher { Flowable.fromIterable(it) }
            .map(CatalogItemMapper::mapToCatalogItemUi)
            .toList()
    }

    fun getCatalogItems() : List<CatalogItemUI> {
        val call = CatalogAPI.retrofit.create(CatalogAPI::class.java)
            .getImages()
        call.clone().enqueue(object : Callback<List<ImageServiceResponse>> {
            override fun onResponse(call: Call<List<ImageServiceResponse>>, response: Response<List<ImageServiceResponse>>) {
                if (response.code() == 200) {
                    list = handleSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<List<ImageServiceResponse>>, t: Throwable) {
                //Log.i(this.javaClass.toString(), t.message.toString())
            }
        })
    }
    private fun handleSuccess(response: List<ImageServiceResponse>?) : List<Product>{
        val listOfProducts = mutableListOf<Product>()
        response?.forEach { product ->
            product.products.map {
                listOfProducts.add( Product(
                    id = it.id,
                    categoryId = it.categoryId,
                    name = it.name,
                    description = it.description,
                    url = it.url
                )
                )
            }
        }
        return listOfProducts
    }
}