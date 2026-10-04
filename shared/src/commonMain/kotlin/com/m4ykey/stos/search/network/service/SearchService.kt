package com.m4ykey.stos.search.network.service

import com.m4ykey.stos.core.Items
import com.m4ykey.stos.question.network.model.QuestionItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments

class SearchService(private val client : HttpClient) : RemoteSearchService {

    override suspend fun searchQuestions(
        order: String,
        sort: String,
        site: String,
        filter: String,
        page: Int,
        pageSize: Int,
        inTitle: String
    ): Items<QuestionItemDto> {
        return client.get {
            url {
                appendPathSegments("search")
                parameter("order", order)
                parameter("sort", sort)
                parameter("site", site)
                parameter("filter", filter)
                parameter("page", page)
                parameter("page_size", pageSize)
                parameter("intitle", inTitle)
            }
        }.body()
    }
}