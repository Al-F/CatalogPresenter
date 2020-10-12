package com.example.imageloader.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val retrofit: Retrofit
) : ViewModel() {

    @Inject
    lateinit var listOfItems: Call<List<CatalogResponse>>

    fun loadCatalog() {
        Log.i(this::class.java.toString(), "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    private fun handleItems() {
        listOfItems.enqueue(object : Callback<List<CatalogResponse>> {
            override fun onResponse(
                call: Call<List<CatalogResponse>>,
                response: Response<List<CatalogResponse>>
            ) {
                if (response.code() == 200) {
                    handleSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<List<CatalogResponse>>, t: Throwable) {
                Log.i(this.javaClass.toString(), t.message.toString())
            }
        })
    }

    private fun handleSuccess(response: List<CatalogResponse>?): List<Product> {
        val listOfProducts = mutableListOf<Product>()
        response?.forEach { product ->
            product.products.map {
                listOfProducts.add(
                    Product(
                        id = it.id,
                        categoryId = it.categoryId,
                        name = it.name,
                        description = it.description,
                        url = it.url
                    )
                )
            }
        }
        Log.i(this::javaClass.toString(), listOfProducts.toString())
        return listOfProducts
    }
}