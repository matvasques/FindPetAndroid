package com.findpet.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.findpet.BuildConfig
import com.findpet.UserRegister.datasource.UserLocalDataSource
import com.findpet.animallist.view.AnimalRepository
import com.findpet.animallist.view.AnimalViewModel
import com.findpet.home.repository.HomeRepository
import com.findpet.home.view.HomeViewModel
import com.findpet.login.UserRepository
import com.findpet.login.UserViewModel
import com.findpet.network.AnimalApi
import com.findpet.network.AuthInterceptor
import com.findpet.network.UserApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { AnimalViewModel(get()) }
    viewModel { UserViewModel(get()) }
    viewModel { HomeViewModel() }
}

val repositoryModule = module {
    single { AnimalRepository(get()) }
    single { HomeRepository() }
    single { UserRepository(get(), get()) }
}

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideUserApi(get()) }
    factory { provideAnimalApi(get()) }
    single { provideRetrofit(get()) }
}

val dataSourceModule = module {
    single { UserLocalDataSource(androidContext()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor()).build()
}

fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)
fun provideAnimalApi(retrofit: Retrofit): AnimalApi = retrofit.create(AnimalApi::class.java)