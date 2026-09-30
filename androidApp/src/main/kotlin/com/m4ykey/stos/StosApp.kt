package com.m4ykey.stos

import android.app.Application
import com.m4ykey.stos.di.initModule
import org.koin.android.ext.koin.androidContext

class StosApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initModule {
            androidContext(this@StosApp)
        }
    }

}