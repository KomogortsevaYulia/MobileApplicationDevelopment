package com.example.myapplication.presentation.adapter.book

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.model.Item
import com.example.myapplication.presentation.search.SearchFragment

class BookRecyclerViewAdapter(
    private val bookList: List<Item>,
    private val activity: Activity,
    private val onBookCardClickListener: OnBookCardClickListener,
    private val isDeletable: Boolean = false
): RecyclerView.Adapter<BookRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookRecyclerViewHolder(inflater, parent, activity, onBookCardClickListener, isDeletable)
    }

    override fun onBindViewHolder(holder: BookRecyclerViewHolder, position: Int) {
        Log.d("BRUHX", "${position}")
        val item: Item = bookList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = bookList.size

    fun getList() = bookList
}