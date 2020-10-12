package com.example.imageloader.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.mapper.CatalogItemMapper
import com.example.imageloader.ui.error.Failure
import com.example.imageloader.ui.model.CatalogItemUI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val retrofit: Retrofit
) : ViewModel() {

    private val failure: MutableLiveData<Failure> = MutableLiveData()

    @Inject
    lateinit var listOfItems: Call<List<CatalogResponse>>

    private val _items = MutableLiveData<List<CatalogItemUI>>()
    private val items: LiveData<List<CatalogItemUI>> = _items

    fun observeFailure(): LiveData<Failure> = failure

    fun observeItems(): LiveData<List<CatalogItemUI>> = items

    fun loadCatalog() {
        if (_items.value == null) {
            requestCatalogFromServer()
        }
    }

    private fun requestCatalogFromServer() {
        listOfItems.enqueue(object : Callback<List<CatalogResponse>> {
            override fun onResponse(
                call: Call<List<CatalogResponse>>,
                response: Response<List<CatalogResponse>>
            ) {
                if (response.code() == 200) {
                    _items.value = CatalogItemMapper.mapToCatalogItemsUi(response.body())
                }
            }

            override fun onFailure(call: Call<List<CatalogResponse>>, t: Throwable) {
                handleFailure(Failure.ServerError)
            }
        })
    }


    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}