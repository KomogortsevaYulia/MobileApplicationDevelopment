package com.example.myapplication.data.repositopy

import com.example.myapplication.data.local.BookDao
import com.example.myapplication.data.remote.BookApi
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import com.example.myapplication.domain.repository.BookRepository
import io.reactivex.Single
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookApi: BookApi,
    private val bookDao: BookDao
): BookRepository {

    override fun saveBook(item: Item) {
        bookDao.insertBook(item)
    }

    override fun removeBookById(id: String) {
        bookDao.removeBookById(id)
    }

    override fun getBookByTitle(title: String): Single<Book> {
        return bookApi.getBookByTitle(title)
    }

    override fun getBookItemById(id: String): Single<Item> {
        return bookApi.getBookItemById(id)
    }

    override fun getFavoriteBookList(): Single<List<Item>> {
        return bookDao.getFavoriteBookList()
    }
}