package com.findpet

import android.app.Application
import com.findpet.di.repositoryModule
import com.findpet.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(viewModelModule, repositoryModule)
        }
    }
}