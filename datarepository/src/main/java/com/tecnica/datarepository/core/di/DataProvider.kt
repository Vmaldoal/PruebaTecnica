package com.tecnica.datarepository.core.di

import com.tecnica.datarepository.core.network.ApiServices
import com.tecnica.datarepository.BuildConfig
import com.tecnica.domaincontroller.character.repository.CharacterRepository
import com.tecnica.datarepository.character.CharacterRepositoryImpl
import com.tecnica.datarepository.character.datasource.CharacterCacheDataSource
import com.tecnica.datarepository.character.datasource.CharacterCacheDataSourceImpl
import com.tecnica.datarepository.character.datasource.CharacterRemoteDataSource
import com.tecnica.datarepository.character.datasource.CharacterRemoteDataSourceImpl
import com.tecnica.datarepository.core.network.HeadersInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module() {

    single<ApiServices> {
        val httpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(
                HeadersInterceptor()
            )

            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        val retrofit = Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiServices::class.java)
    }

    single<CharacterRemoteDataSource> { CharacterRemoteDataSourceImpl(get()) }
    single<CharacterCacheDataSource> { CharacterCacheDataSourceImpl() }
    single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
}