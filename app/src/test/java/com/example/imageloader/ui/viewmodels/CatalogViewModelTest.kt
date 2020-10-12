package com.example.imageloader.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.imageloader.data.model.CatalogResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call

@RunWith(MockitoJUnitRunner::class)
class CatalogViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    lateinit var catalogViewModel: CatalogViewModel

    @Mock
    lateinit var listOfItems: Call<List<CatalogResponse>>

    @Before
    fun setUp() {
        catalogViewModel = CatalogViewModel(listOfItems)
    }

    @Test
    fun shouldLoadValues() {
        catalogViewModel.loadCatalog()
        Mockito.verify(listOfItems).enqueue(Mockito.any())
    }
}