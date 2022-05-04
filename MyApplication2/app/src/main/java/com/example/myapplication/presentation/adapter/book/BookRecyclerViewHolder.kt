package com.example.myapplication.presentation.adapter.book

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.CardBookBinding
import com.example.myapplication.domain.model.Item
import com.example.myapplication.presentation.adapter.book_forms.BookFormsRecyclerViewAdapter

class BookRecyclerViewHolder (
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val activity: Activity,
    private val onPokemonCardClickListener: OnBookCardClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_book, parent, false)) {


    private val mBinding by viewBinding(CardBookBinding::bind)

    fun bind(item: Item) {
        mBinding.tvPokemonName.text = item.volumeInfo?.title

        mBinding.btnCardAddInFavorite.text = activity.getString(R.string.card_btn_favorite_add)
        mBinding.btnCardAddInFavorite.setOnClickListener {
            onPokemonCardClickListener.onAddToFavoriteClicked(
                item = item,
                position = absoluteAdapterPosition
            )
        }

        item.volumeInfo?.imageLinks?.smallThumbnail?.let { loadPokemonImage(it) }
    }

    private fun loadPokemonImage(url: String) {
        Glide
            .with(activity)
            .load(url)
            .into(mBinding.ivPokemonImage)
    }
}