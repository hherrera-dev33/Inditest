package mobi.dev33.inditest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import mobi.dev33.inditest.data.datasource.UserPagingSource
import mobi.dev33.inditest.data.datasource.api.ApiClient
import mobi.dev33.inditest.data.datasource.api.ApiDataSource
import mobi.dev33.inditest.data.repository.UserRepositoryImpl
import mobi.dev33.inditest.domain.repository.IUserRepository
import mobi.dev33.inditest.domain.usecase.GetUsersUsecase
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UsecaseModule {

    @Provides
    @ViewModelScoped
    fun getUsersUsecaseProvider(userRepository: IUserRepository) = GetUsersUsecase(userRepository)
}