package com.example.tvprogramapp

import android.app.Application
import com.example.tvprogramapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(netModule, viewModelModule, repositoryModule, adaptersModule, mainModule)
        }
    }
}