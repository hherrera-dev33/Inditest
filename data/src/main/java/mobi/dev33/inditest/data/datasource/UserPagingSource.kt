package mobi.dev33.inditest.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import mobi.dev33.inditest.data.datasource.api.ApiDataSource
import mobi.dev33.inditest.data.datasource.api.model.ApiUser

class UserPagingSource(private val apiDataSource: ApiDataSource) : PagingSource<Int, ApiUser>() {
    override fun getRefreshKey(state: PagingState<Int, ApiUser>) =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.run { prevKey?.plus(1) ?: nextKey?.minus(1) }
        }

    override suspend fun load(params: LoadParams<Int>) = try {
        val response = apiDataSource.getUserList(params.key ?: 1)
        LoadResult.Page(response.results, null, response.info.page + 1)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

}