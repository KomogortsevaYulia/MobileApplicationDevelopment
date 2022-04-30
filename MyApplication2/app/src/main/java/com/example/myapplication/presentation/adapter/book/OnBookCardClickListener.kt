package com.example.myapplication.presentation.adapter.book

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item

interface  OnBookCardClickListener {
    fun onAddToFavoriteClicked(item: Item, position: Int)
}