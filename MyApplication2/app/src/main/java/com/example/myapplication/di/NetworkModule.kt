package com.example.myapplication.di

import com.example.myapplication.data.remote.BookApi
import com.example.myapplication.data.remote.BookApiClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitClient(): BookApi {
        return BookApiClient.getClient()
    }
}