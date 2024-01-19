package mobi.dev33.inditest.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import mobi.dev33.inditest.domain.model.DomainUser
import mobi.dev33.inditest.domain.repository.IUserRepository

class GetUsersUsecase(private val userRepository: IUserRepository) {
    suspend operator fun invoke(): Flow<PagingData<DomainUser>> {
        return userRepository.getUserList().distinctUntilChanged()
    }

}