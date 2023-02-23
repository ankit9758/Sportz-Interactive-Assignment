package com.example.sportzinteractivedemo.match.di

import com.example.sportzinteractivedemo.match.network.ApiService
import com.example.sportzinteractivedemo.match.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: ApiService,
    ): MainRepository =
        MainRepository(apiService)

}