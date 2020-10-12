package com.example.imageloader.api

import com.example.imageloader.data.model.CatalogResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CatalogAPI {
    @GET(".")
    fun getImages(): Call<List<CatalogResponse>>
}

