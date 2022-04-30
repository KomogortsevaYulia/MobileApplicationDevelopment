package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.BookDao
import com.example.myapplication.data.repositopy.BookRepositoryImpl
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class RemoveBookUseCase @Inject constructor(
    private val bookRepositoryImpl: BookRepositoryImpl
) {

    fun removeBookById(id: String) {
        bookRepositoryImpl.removeBookById(id)
    }
}