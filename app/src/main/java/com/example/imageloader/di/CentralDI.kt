package com.example.imageloader.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.imageloader.CatalogApplication
import com.example.imageloader.api.CatalogAPI
import com.example.imageloader.data.model.CatalogResponse
import com.example.imageloader.ui.fragment.CatalogFragment
import com.example.imageloader.ui.fragment.CatalogViewModel
import dagger.*
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.internal.Beta
import dagger.multibindings.IntoMap
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, CatalogModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<CatalogApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}

@Beta
@Module(includes = [AndroidInjectionModule::class])
abstract class AndroidSupportInjectionModule private constructor()

@Module
abstract class CatalogModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun catalogFragment(): CatalogFragment

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    abstract fun bindViewModel(viewmodel: CatalogViewModel): ViewModel
}

@Module
class NetworkModule {
    companion object {
        private const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com/"
        private val MIME_APPLICATION_JSON = MediaType.get("application/json")
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Provides
    fun provideCatalogItemsList(retrofit: Retrofit): Call<List<CatalogResponse>> {
        return retrofit.create(CatalogAPI::class.java).getImages()
    }

//    @Provides
//    fun provideCatalogRepository(catalogAPI: CatalogAPI): CatalogRepository {
//        return CatalogRepository(catalogAPI)
//    }
//
//    @Provides
//    fun provideCatalogInteractor(catalogRepository: CatalogRepository): CatalogService {
//        return CatalogService(catalogRepository)
//    }
}