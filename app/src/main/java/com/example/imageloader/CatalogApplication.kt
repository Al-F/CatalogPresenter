package com.example.imageloader

import android.app.Application
import com.example.imageloader.di.AppComponent
import com.example.imageloader.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CatalogApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.factory().create(applicationContext)
    }
}