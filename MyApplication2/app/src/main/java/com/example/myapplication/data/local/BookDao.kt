package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(item: Item)

    @Query("SELECT * FROM item_table")
    fun getFavoriteBookList(): Single<List<Item>>

    @Query("DELETE FROM item_table WHERE id = :id")
    fun removeBookById(id: String)
}