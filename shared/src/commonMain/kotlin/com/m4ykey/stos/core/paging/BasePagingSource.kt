package com.m4ykey.stos.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlin.coroutines.cancellation.CancellationException

abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val result = loadData(page, pageSize)

            result.fold(
                onSuccess = { pageResult ->
                    LoadResult.Page(
                        data = pageResult.items,
                        nextKey = if (pageResult.hasMore) page + 1 else null,
                        prevKey = if (page == 1) null else page - 1
                    )
                },
                onFailure = { exception ->
                    if (exception is CancellationException) throw exception
                    LoadResult.Error(exception)
                }
            )
        } catch (e : CancellationException) {
            throw e
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    protected abstract suspend fun loadData(page : Int, pageSize : Int) : Result<PageResult<Value>>

}