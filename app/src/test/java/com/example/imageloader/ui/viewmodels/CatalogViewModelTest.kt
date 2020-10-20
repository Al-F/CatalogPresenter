package com.example.imageloader.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.ui.model.CatalogItemUi
import com.example.imageloader.ui.model.SalePriceUi
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call

@RunWith(MockitoJUnitRunner::class)
class CatalogViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var catalogViewModel: CatalogViewModel
    var listOfItems = mockk<Call<List<CatalogResponse>>>()

    //every enqueue should return values
    var catalogItem = CatalogItemUi(0, 0, "name", "url", "description", SalePriceUi("3.34", "usd"))

    @Before
    fun setUp() {
        catalogViewModel = CatalogViewModel(listOfItems)
    }

    @Test
    fun shouldLoadValues() {
        catalogViewModel.loadCatalog()
        Mockito.verify(listOfItems).enqueue(Mockito.any())
    }

    @Test
    fun shouldNotLoadValuesIfPreviouslyLoaded() {
        //listOfItems Enqueue Should Return values then second call for load will not be executed
    }

    @Test
    fun onCatalogItemSelectedShouldSetSelectedItem() {
        catalogViewModel.onItemSelected(catalogItem)
        catalogViewModel.selectedItem shouldBeEqualTo catalogItem
    }
}