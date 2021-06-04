package com.lemick.fodmapscanner

import android.app.Application
import com.lemick.fodmapscanner.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(mainModule)
        }
    }
}