package mobi.dev33.inditest.data.datasource.api

import mobi.dev33.inditest.data.BuildConfig
import mobi.dev33.inditest.data.datasource.api.model.response.ApiUserListResponse
import mobi.dev33.inditest.domain.repository.IUserRepository
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDataSource {

    @GET("/api?exc=login,nat,id")
    suspend fun getUserList(
        @Query("page") page: Int,
        @Query("results") pageSize: Int = IUserRepository.PAGE_SIZE,
        @Query("seed") seed: String = BuildConfig.API_SEED,
    ): ApiUserListResponse
}