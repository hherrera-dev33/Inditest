package mobi.dev33.inditest.data.datasource.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun buildApiClient() =
        Retrofit.Builder().baseUrl("https://randomuser.me/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor() { Log.d("API", it) }.setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiDataSource::class.java)
}