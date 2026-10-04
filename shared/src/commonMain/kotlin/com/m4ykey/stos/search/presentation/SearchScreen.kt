package com.m4ykey.stos.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.m4ykey.stos.core.ui.AppScaffold
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.back
import stos.shared.generated.resources.ic_arrow_back
import stos.shared.generated.resources.ic_close
import stos.shared.generated.resources.ic_search
import stos.shared.generated.resources.search

@Composable
fun SearchScreen(
    onBack : () -> Unit,
    onSearch: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }

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
            SearchScreenContent(
                value = value,
                onValueChange = { value = it },
                onSearch = { onSearch(value) }
            )
        }
    )
}

@Composable
fun SearchScreenContent(
    value : String,
    onValueChange: (String) -> Unit,
    onSearch : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        SearchText(
            value = value,
            onValueChange = onValueChange,
            onSearch = onSearch
        )
    }
}

@Composable
fun SearchText(
    modifier : Modifier = Modifier,
    onSearch : () -> Unit,
    value : String,
    onValueChange : (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyUp && event.key == Key.Enter) {
                    onSearch()
                    true
                } else {
                    false
                }
            },
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                contentDescription = null,
                painter = painterResource(Res.drawable.ic_search),
                modifier = Modifier.size(24.dp)
            )
        },
        singleLine = true,
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        contentDescription = null,
                        painter = painterResource(Res.drawable.ic_close)
                    )
                }
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearch()
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        placeholder = { Text(stringResource(Res.string.search) + "...") }
    )
}