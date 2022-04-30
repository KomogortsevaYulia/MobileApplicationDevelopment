package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.BookDB
import com.example.myapplication.data.local.BookDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): BookDB {
        return Room.databaseBuilder(
            context,
            BookDB::class.java,
            "book_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBookDao(bookDB: BookDB): BookDao {
        return bookDB.getBookDao()
    }
}