package com.m4ykey.stos.search.domain.model

import com.m4ykey.stos.core.SortOption
import org.jetbrains.compose.resources.StringResource
import stos.shared.generated.resources.Res
import stos.shared.generated.resources.activity
import stos.shared.generated.resources.creation
import stos.shared.generated.resources.relevance
import stos.shared.generated.resources.votes

enum class SearchSort(override val labelRes: StringResource) : SortOption {
    ACTIVITY(Res.string.activity),
    VOTES(Res.string.votes),
    CREATION(Res.string.creation),
    RELEVANCE(Res.string.relevance)
}