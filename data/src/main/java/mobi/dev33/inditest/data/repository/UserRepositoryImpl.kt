package mobi.dev33.inditest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import kotlinx.coroutines.flow.map
import mobi.dev33.inditest.data.datasource.UserPagingSource
import mobi.dev33.inditest.data.datasource.api.ApiDataSource
import mobi.dev33.inditest.data.datasource.api.model.mapper.toDomain
import mobi.dev33.inditest.domain.repository.IUserRepository

class UserRepositoryImpl(private val apiDataSource: ApiDataSource) : IUserRepository {

    override suspend fun getUserList() = Pager(
        pagingSourceFactory = { UserPagingSource(apiDataSource) },
        config = PagingConfig(IUserRepository.PAGE_SIZE)
    ).flow.map {  it.map { user -> user.toDomain() } }
}