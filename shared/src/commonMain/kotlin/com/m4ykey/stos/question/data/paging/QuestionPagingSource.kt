package com.m4ykey.stos.question.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.m4ykey.stos.question.domain.model.QuestionItem
import com.m4ykey.stos.question.data.mapper.toQuestionItem
import com.m4ykey.stos.question.data.network.service.RemoteQuestionService

class QuestionPagingSource(
    private val service : RemoteQuestionService,
    private val site : String,
    private val sort : String
) : PagingSource<Int, QuestionItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuestionItem> {
        val page = params.key ?: 1

        return try {
            val response = service.getQuestions(
                page = page,
                pageSize = params.loadSize,
                sort = sort,
                site = site
            )

            LoadResult.Page(
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.has_more) page + 1 else null,
                data = response.items.map { it.toQuestionItem() }
            )

        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, QuestionItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}