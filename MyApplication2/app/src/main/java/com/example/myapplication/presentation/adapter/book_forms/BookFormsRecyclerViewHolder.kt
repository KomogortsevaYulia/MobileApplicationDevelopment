package com.example.myapplication.presentation.adapter.book_forms

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.CardItemBookFormsBinding
import com.example.myapplication.domain.model.Item

class BookFormsRecyclerViewHolder (
    inflater: LayoutInflater,
    parent: ViewGroup
): RecyclerView.ViewHolder(inflater.inflate(R.layout.card_item_book_forms, parent, false)) {

    private val mBinding by viewBinding(CardItemBookFormsBinding::bind)

    fun bind(form: Item) {
        //mBinding.tvItemPokemonForm.text = form
    }
}