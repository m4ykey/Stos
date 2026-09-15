package com.m4ykey.stos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.m4ykey.stos.navigation.NavigationRoot

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold { innerPadding ->
            NavigationRoot(modifier = Modifier.padding(innerPadding))
        }
    }
}