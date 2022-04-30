package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import io.reactivex.Observable
import io.reactivex.Single

interface BookRepository {
    fun saveBook(item: Item)

    fun removeBookById(id: String)

    fun getBookByTitle(title: String): Single<Book>

    fun getBookItemById(id: String): Single<Item>

    fun getFavoriteBookList(): Single<List<Item>>
}