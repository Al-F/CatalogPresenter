package com.example.api

import com.example.model.ImageServiceResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com/"

interface CatalogAPI {
    @GET(".")
    fun getImages(): Call<List<ImageServiceResponse>>

    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

