package mobi.dev33.inditest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mobi.dev33.inditest.data.datasource.UserPagingSource
import mobi.dev33.inditest.data.datasource.api.ApiClient
import mobi.dev33.inditest.data.datasource.api.ApiDataSource
import mobi.dev33.inditest.data.repository.UserRepositoryImpl
import mobi.dev33.inditest.domain.repository.IUserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun apiDataSourceProvider() = ApiClient.buildApiClient()


    @Provides
    @Singleton
    fun userRepositoryProvider(apiDataSource: ApiDataSource): IUserRepository =
        UserRepositoryImpl(apiDataSource)

}