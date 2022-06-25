package com.mr.fodmapscanner

import android.app.Application
import com.mr.fodmapscanner.di.mainModule
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