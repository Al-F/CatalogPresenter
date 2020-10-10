package com.example.imageloader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.api.CatalogAPI
import com.example.model.ImageServiceResponse
import com.example.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var list: List<Product> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       getImages()
        print(list)
    }

    fun getImages() {
        val call = CatalogAPI.retrofit.create(CatalogAPI::class.java)
            .getImages()
        call.clone().enqueue(object : Callback<List<ImageServiceResponse>> {
            override fun onResponse(call: Call<List<ImageServiceResponse>>, response: Response<List<ImageServiceResponse>>) {
                if (response.code() == 200) {
                    list = handleSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<List<ImageServiceResponse>>, t: Throwable) {
                Log.e(this.javaClass.toString(), t.message.toString())
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
                ))
            }
        }
        return listOfProducts
    }
}

