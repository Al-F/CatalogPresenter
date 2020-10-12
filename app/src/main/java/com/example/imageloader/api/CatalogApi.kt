package com.example.imageloader.api

import com.example.imageloader.data.model.CatalogResponse
import retrofit2.Call
import retrofit2.http.GET

interface CatalogApi {
    @GET(".")
    fun getImages(): Call<List<CatalogResponse>>
}

