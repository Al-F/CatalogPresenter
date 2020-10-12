package com.example.imageloader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imageloader.ui.fragment.CatalogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CatalogFragment.newInstance())
                .commitNow()
        }
    }
}

