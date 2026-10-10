package com.m4ykey.stos.user.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.m4ykey.stos.core.formatReputation
import com.m4ykey.stos.core.network.openBrowser
import com.m4ykey.stos.core.ui.ActionButton
import com.m4ykey.stos.core.ui.AppScaffold
import com.m4ykey.stos.core.ui.ErrorItem
import com.m4ykey.stos.core.ui.LoadingItem
import com.m4ykey.stos.question.domain.model.BadgeCounts
import com.m4ykey.stos.user.presentation.components.BadgeCountsRow
import com.m4ykey.stos.user.presentation.components.UserCard
import com.m4ykey.stos.user.presentation.state.UserState
import com.m4ykey.text_markdown.TextMarkdown
import org.koin.compose.viewmodel.koinViewModel
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.back
import stos.shared.generated.resources.ic_arrow_back
import stos.shared.generated.resources.ic_link
import stos.shared.generated.resources.link

@Composable
fun UserScreen(
    modifier : Modifier = Modifier,
    userId : Int,
    onBack : () -> Unit,
    viewModel: UserViewModel = koinViewModel()
) {
    val listState = rememberLazyListState()

    val state by viewModel.userState.collectAsStateWithLifecycle()

    LaunchedEffect(userId) {
        viewModel.getUserById(userId)
    }

    AppScaffold(
        content = {
            UserScreenContent(
                state = state,
                listState = listState,
                onRetry = { viewModel.onRetryUserState(userId) }
            )
        },
        navigation = {
            ActionButton(
                onClick = onBack,
                text = Res.string.back,
                icon = Res.drawable.ic_arrow_back
            )
        },
        actions = {
            ActionButton(
                icon = Res.drawable.ic_link,
                text = Res.string.link,
                onClick = {
                    state.user?.let { user ->
                        openBrowser(user.link)
                    }
                }
            )
        }
    )
}

@Composable
fun UserScreenContent(
    modifier : Modifier = Modifier,
    state : UserState,
    listState : LazyListState,
    onRetry : () -> Unit
) {
    when {
        state.isLoading -> LoadingItem()
        state.error != null -> ErrorItem(
            message = state.error,
            onRetry = { onRetry() }
        )
        else -> {
            val item = state.user

            if (item != null) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    item {
                        UserProfile(
                            profileImage = item.profileImage,
                            displayName = item.displayName,
                            reputation = item.reputation,
                            badgeCounts = item.badgeCounts
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfile(
    profileImage : String,
    displayName : String,
    reputation : Int,
    badgeCounts : BadgeCounts
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserCard(
                profileImage = profileImage,
                size = 200.dp
            )
            TextMarkdown(
                text = displayName
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = formatReputation(reputation)
                )
                Spacer(modifier = Modifier.width(10.dp))
                BadgeCountsRow(badgeCounts = badgeCounts)
            }
        }
    }
}