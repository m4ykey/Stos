package com.m4ykey.stos

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.m4ykey.stos.di.initModule
import androidx.compose.ui.Alignment

fun main() = application {
    initModule()

    val windowState = rememberWindowState(
        width = 1200.dp,
        height = 800.dp,
        position = WindowPosition.Aligned(Alignment.Center)
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Stos",
        alwaysOnTop = true,
        resizable = true,
        state = windowState
    ) {
        App()
    }
}