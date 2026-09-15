package com.m4ykey.stos.question.data.network.service

import com.m4ykey.stos.question.data.network.dto.QuestionDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments

class QuestionService(
    private val client : HttpClient
) : RemoteQuestionService {

    override suspend fun getQuestions(
        order: String,
        sort: String,
        site: String,
        filter: String,
        page: Int,
        pageSize: Int
    ): QuestionDto {
        return client.get {
            url {
                appendPathSegments("questions")
                parameter("order", order)
                parameter("sort", sort)
                parameter("site", site)
                parameter("filter", filter)
                parameter("page", page)
                parameter("pagesize", pageSize)
            }
        }.body()
    }
}