package com.example.myapplication.di

import com.example.myapplication.data.local.BookDao
import com.example.myapplication.data.remote.BookApi
import com.example.myapplication.data.repositopy.BookRepositoryImpl
import com.example.myapplication.domain.repository.BookRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providePokemonRepository(bookApi: BookApi, bookDao: BookDao): BookRepository {
        return BookRepositoryImpl(bookApi = bookApi, bookDao = bookDao)
    }
}