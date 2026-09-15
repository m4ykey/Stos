package com.m4ykey.stos.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.m4ykey.stos.question.presentation.QuestionScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavigationRoot(
    modifier : Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.QuestionHome::class, Route.QuestionHome.serializer())
                    subclass(Route.Search::class, Route.Search.serializer())
                    subclass(Route.QuestionDetail::class, Route.QuestionDetail.serializer())
                }
            }
        },
        Route.QuestionHome
    )

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        transitionSpec = {
            slideInHorizontally { it } togetherWith
                    slideOutHorizontally { -it }
        },
        popTransitionSpec = {
            slideInHorizontally { -it } togetherWith
                    slideOutHorizontally { it }
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.QuestionHome> {
                QuestionScreen(
                    onSearchClick = {
                        // TODO: Navigate to search
                    },
                    onQuestionClick = {
                        // TODO: Navigate to question detail
                    }
                )
            }
            entry<Route.Search> {
                // TODO: Add search screen
            }
            entry<Route.QuestionDetail> {
                // TODO: Add question detail screen
            }
        }
    )
}