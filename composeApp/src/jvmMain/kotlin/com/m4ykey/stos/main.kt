package com.m4ykey.stos

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.m4ykey.stos.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kmp-stos",
        alwaysOnTop = true
    ) {
        App()
    }
}