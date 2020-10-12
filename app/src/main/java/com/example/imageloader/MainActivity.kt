package com.example.imageloader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imageloader.ui.fragment.CatalogFragment
import com.example.imageloader.ui.fragment.ItemDetailsFragment
import com.example.imageloader.ui.model.CatalogItemUI

class MainActivity : AppCompatActivity(), CatalogFragment.OnCatalogItemSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CatalogFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCatalogItemSelected(model: CatalogItemUI) {
        val itemDetailsFragment = ItemDetailsFragment.newInstance(
            model.url,
            "${model.name}: ${model.salePrice.amount} ${model.salePrice.currency}"
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, itemDetailsFragment, "itemDetails")
            .addToBackStack(null).commit()
    }
}

