package com.m4ykey.stos.core.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppScaffold(
    modifier : Modifier = Modifier,
    title : String? = null,
    content : @Composable (PaddingValues) -> Unit,
    actions : @Composable RowScope.() -> Unit = {},
    navigation : @Composable () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    if (title != null) {
                        Text(text = title)
                    }
                },
                actions = { actions() },
                navigationIcon = { navigation() }
            )
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}