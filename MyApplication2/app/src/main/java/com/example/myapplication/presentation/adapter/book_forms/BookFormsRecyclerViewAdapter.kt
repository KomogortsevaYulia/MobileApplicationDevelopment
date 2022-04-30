package com.example.myapplication.presentation.adapter.book_forms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.model.Item

class BookFormsRecyclerViewAdapter (
    private val stats: List<Item>
): RecyclerView.Adapter<BookFormsRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFormsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookFormsRecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BookFormsRecyclerViewHolder, position: Int) {
        val stat: Item = stats[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int = stats.size
}