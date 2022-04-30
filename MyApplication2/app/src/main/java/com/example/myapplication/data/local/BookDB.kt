package com.example.myapplication.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item

@Database(entities = [Book::class, Item::class], version = 1)
abstract class BookDB: RoomDatabase() {
    abstract fun getBookDao(): BookDao
}