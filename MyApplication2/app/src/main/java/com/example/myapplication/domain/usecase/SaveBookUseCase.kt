package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class SaveBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    fun saveBook(item: Item) {
        bookRepository.saveBook(item)
    }


}