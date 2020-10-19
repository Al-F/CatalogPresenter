package com.example.imageloader

import com.example.imageloader.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CatalogApplication : DaggerApplication() {
    companion object {
        const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com"
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}