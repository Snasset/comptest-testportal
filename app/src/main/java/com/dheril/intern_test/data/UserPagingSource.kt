package com.dheril.intern_test.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dheril.intern_test.data.response.DataItem
import com.dheril.intern_test.data.response.UsersResponse
import com.dheril.intern_test.data.retrofit.ApiConfig

class UserPagingSource(): PagingSource<Int, DataItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = ApiConfig.getApiService().getUsers(position, params.loadSize)
            val listData = responseData.data
            LoadResult.Page(
                data = listData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (listData.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}