package com.example.myapplication.domain.usecase

import com.example.myapplication.data.repositopy.BookRepositoryImpl
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import com.example.myapplication.domain.repository.BookRepository
import io.reactivex.Single
import javax.inject.Inject

class GetBookUseCase @Inject constructor(
    private val repository: BookRepositoryImpl
) {

    fun getBookByName(name: String): Single<Book> = repository.getBookByTitle(title = name)

    fun getBookItemById(id: String): Single<Item> = repository.getBookItemById(id = id)

    fun getFavoriteBookList(): Single<List<Item>> = repository.getFavoriteBookList()
}