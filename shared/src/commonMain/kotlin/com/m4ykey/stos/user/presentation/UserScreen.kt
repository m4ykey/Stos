package com.m4ykey.stos.user.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.m4ykey.stos.core.ui.ActionButton
import com.m4ykey.stos.core.ui.AppScaffold
import org.koin.compose.viewmodel.koinViewModel
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.back
import stos.shared.generated.resources.ic_arrow_back

@Composable
fun UserScreen(
    modifier : Modifier = Modifier,
    userId : Int,
    onBack : () -> Unit,
    viewModel: UserViewModel = koinViewModel()
) {
    AppScaffold(
        content = {
            UserScreenContent(
                viewModel = viewModel
            )
        },
        navigation = {
            ActionButton(
                onClick = onBack,
                text = Res.string.back,
                icon = Res.drawable.ic_arrow_back
            )
        }
    )
}

@Composable
fun UserScreenContent(
    modifier : Modifier = Modifier,
    viewModel: UserViewModel
) {}