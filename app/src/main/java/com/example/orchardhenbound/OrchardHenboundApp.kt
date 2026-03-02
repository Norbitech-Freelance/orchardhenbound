package com.example.orchardhenbound

import android.app.Application
import com.example.orchardhenbound.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OrchardHenboundApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@OrchardHenboundApp)
            modules(appModule)
        }
    }
}
