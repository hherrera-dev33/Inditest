package mobi.dev33.inditest.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mobi.dev33.inditest.domain.model.DomainUser

interface IUserRepository {

    companion object {
        const val PAGE_SIZE = 20
    }

    suspend fun getUserList(): Flow<PagingData<DomainUser>>
}