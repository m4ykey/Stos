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
import com.m4ykey.stos.question.presentation.QuestionDetailScreen
import com.m4ykey.stos.question.presentation.QuestionHomeScreen
import com.m4ykey.stos.search.presentation.SearchListScreen
import com.m4ykey.stos.search.presentation.SearchScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AppNavigation(modifier : Modifier = Modifier) {

    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.QuestionHome::class, Route.QuestionHome.serializer())
                    subclass(Route.SearchScreen::class, Route.SearchScreen.serializer())
                    subclass(Route.SearchList::class, Route.SearchList.serializer())
                    subclass(Route.QuestionDetail::class, Route.QuestionDetail.serializer())
                }
            }
        },
        Route.QuestionHome
    )

    fun navigateTo(route : Route) {
        if (rootBackStack.lastOrNull() != route) {
            rootBackStack.add(route)
        }
    }

    fun navigateBack() {
        if (rootBackStack.size > 1) {
            rootBackStack.removeAt(rootBackStack.lastIndex)
        }
    }

    NavDisplay(
        backStack = rootBackStack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            slideInHorizontally { it } togetherWith
                    slideOutHorizontally { -it }
        },
        popTransitionSpec = {
            slideInHorizontally { -it } togetherWith
                    slideOutHorizontally { it }
        },
        entryProvider = entryProvider {
            entry<Route.QuestionHome> {
                QuestionHomeScreen(
                    onSearchClick = {
                        navigateTo(Route.SearchScreen)
                    },
                    onOwnerClick = {},
                    onQuestionClick = { key ->
                        navigateTo(Route.QuestionDetail(key))
                    }
                )
            }
            entry<Route.SearchScreen> {
                SearchScreen(
                    onBack = { navigateBack() },
                    onSearch = { key ->
                        navigateTo(Route.SearchList(key))
                    }
                )
            }
            entry<Route.SearchList> { key ->
                SearchListScreen(
                    onBack = {
                        navigateBack()
                    },
                    inTitle = key.inTitle,
                    onOwnerClick = {},
                    onQuestionClick = { questionId ->
                        navigateTo(Route.QuestionDetail(questionId))
                    }
                )
            }
            entry<Route.QuestionDetail> { key ->
                QuestionDetailScreen(
                    onBack = { navigateBack() },
                    questionId = key.questionId
                )
            }
        }
    )
}