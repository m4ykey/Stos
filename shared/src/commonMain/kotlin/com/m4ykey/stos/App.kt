package com.m4ykey.stos

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.m4ykey.stos.navigation.AppNavigation

@Composable
@Preview
fun App() {
    val dark = isSystemInDarkTheme()

    MaterialTheme(
        colorScheme = if (dark) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {
        AppNavigation(
            modifier = Modifier.fillMaxSize()
        )
    }
}