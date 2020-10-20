package com.example.imageloader.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.mapper.CatalogItemMapper
import com.example.imageloader.ui.error.Failure
import com.example.imageloader.ui.model.CatalogItemUi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private var listOfItems: Call<List<CatalogResponse>>
) : ViewModel() {
    private val failure: MutableLiveData<Failure> = MutableLiveData()
    private val catalogItems = MutableLiveData<List<CatalogItemUi>>()

    private lateinit var _selectedItem: CatalogItemUi
    val selectedItem: CatalogItemUi
        get() = _selectedItem

    fun observeFailure(): LiveData<Failure> = failure
    fun observeItems(): LiveData<List<CatalogItemUi>> = catalogItems

    init {
        if (catalogItems.value == null) {
            requestCatalogFromServer()
        }
    }


    fun reloadCatalog() {
        requestCatalogFromServer()
    }

    private fun requestCatalogFromServer() {
        listOfItems.enqueue(object : Callback<List<CatalogResponse>> {
            override fun onResponse(
                call: Call<List<CatalogResponse>>,
                response: Response<List<CatalogResponse>>
            ) {
                if (response.code() == 200) {
                    catalogItems.value = CatalogItemMapper.mapToCatalogItemsUi(response.body())
                }
            }

            override fun onFailure(call: Call<List<CatalogResponse>>, t: Throwable) {
                handleFailure(Failure.ServerError(t))
            }
        })
    }


    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    fun onItemSelected(catalogItemUi: CatalogItemUi) {
        _selectedItem = catalogItemUi
    }
}