package com.m4ykey.stos

import android.content.Context

object AndroidContext {
    lateinit var appContext : Context
        private set

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }
}