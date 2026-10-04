package com.m4ykey.stos.search.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.m4ykey.stos.core.ui.AppScaffold
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.back
import stos.shared.generated.resources.ic_arrow_back
import stos.shared.generated.resources.search

@Composable
fun SearchScreen(
    onBack : () -> Unit
) {
    AppScaffold(
        title = stringResource(Res.string.search),
        navigation = {
            IconButton(onClick = onBack) {
                Icon(
                    contentDescription = stringResource(Res.string.back),
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        content = {
            SearchScreenContent()
        }
    )
}

@Composable
fun SearchScreenContent() {

}