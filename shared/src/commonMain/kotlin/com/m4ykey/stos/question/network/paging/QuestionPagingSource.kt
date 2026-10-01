package com.m4ykey.stos.question.network.paging

import com.m4ykey.stos.core.paging.BasePagingSource
import com.m4ykey.stos.core.network.ApiResult
import com.m4ykey.stos.core.network.safeApi
import com.m4ykey.stos.core.paging.PageResult
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.mapper.toQuestionItem
import com.m4ykey.stos.question.network.service.RemoteQuestionService

class QuestionPagingSource(
    private val service : RemoteQuestionService,
    private val sort : String
) : BasePagingSource<QuestionItem>() {

    override suspend fun loadData(
        page: Int,
        pageSize: Int
    ): Result<PageResult<QuestionItem>> {
        return safeApi {
            service.getQuestions(page = page, pageSize = pageSize, sort = sort)
        }.run {
            when (this) {
                is ApiResult.Failure -> Result.failure(exception)
                is ApiResult.Success -> {
                    val items = data.items.map { it.toQuestionItem() }
                    Result.success(PageResult(items, hasMore = data.has_more))
                }
            }
        }
    }
}