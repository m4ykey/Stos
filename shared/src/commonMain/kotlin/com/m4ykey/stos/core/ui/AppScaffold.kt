package com.m4ykey.stos.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppScaffold(
    modifier : Modifier = Modifier,
    title : String? = null,
    content : @Composable (PaddingValues) -> Unit,
    actions : @Composable RowScope.() -> Unit = {},
    navigation : @Composable () -> Unit = {},
    scrollBehavior : TopAppBarScrollBehavior? = null
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { title?.let { Text(text = title) } },
                actions = { actions() },
                navigationIcon = { navigation() },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content(paddingValues)
        }
    }
}