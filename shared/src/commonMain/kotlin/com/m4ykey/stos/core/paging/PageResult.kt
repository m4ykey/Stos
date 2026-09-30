package com.m4ykey.stos.core.paging

data class PageResult<T>(
    val items : List<T>,
    val hasMore : Boolean
)
