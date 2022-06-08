package com.example.foodrecipe

import android.app.Application
import com.example.foodrecipe.di.networkModule
import com.example.foodrecipe.di.repositoryModule
import com.example.foodrecipe.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule + viewModelModule + repositoryModule)
        }

    }
}