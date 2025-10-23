package com.m4ykey.stos.app

import android.app.Application
import android.content.Context
import com.m4ykey.stos.di.initModules
import org.koin.android.ext.koin.androidContext

class StosApp : Application() {

    companion object {
        lateinit var appContext : Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        initModules {
            androidContext(this@StosApp)
        }
    }
}