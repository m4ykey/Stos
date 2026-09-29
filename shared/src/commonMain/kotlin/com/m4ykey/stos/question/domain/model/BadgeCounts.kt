package com.m4ykey.stos.question.domain.model

data class BadgeCounts(
    val bronze : Int,
    val gold : Int,
    val silver : Int
) {
    companion object {
        val EMPTY = BadgeCounts(0, 0, 0)
    }
}
