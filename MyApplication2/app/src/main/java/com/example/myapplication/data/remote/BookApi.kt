package com.example.myapplication.data.remote

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {
    @GET("volumes")
    fun getBookByTitle(@Query("q") title: String): Single<Book>

    @GET("{id}")
    fun getBookItemById(@Path("id") id: String): Single<Item>
}