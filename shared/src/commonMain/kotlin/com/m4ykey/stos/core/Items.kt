package com.m4ykey.stos.core

import kotlinx.serialization.Serializable

@Serializable
data class Items<T>(
    val items : List<T>
)
