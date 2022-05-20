package com.example.myapplication.presentation.adapter.book

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.CardBookBinding
import com.example.myapplication.domain.model.Item
import com.example.myapplication.presentation.adapter.book_forms.BookFormsRecyclerViewAdapter
import com.example.myapplication.presentation.favorite.FavoriteFragment
import com.example.myapplication.presentation.search.SearchFragment

class BookRecyclerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val activity: Activity,
    private val onPokemonCardClickListener: OnBookCardClickListener,
    private val isDeletable: Boolean
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_book, parent, false)) {

    private val mBinding by viewBinding(CardBookBinding::bind)

    fun bind(item: Item) {
        mBinding.tvPokemonName.text = item.volumeInfo?.title
        if (item.volumeInfo?.authors!!.isNotEmpty()) {
            Log.d("BRUH", "${item.volumeInfo?.authors}")
            mBinding.tvPokemonAuthor.text = item.volumeInfo?.authors
                ?.get(0)
                ?.replace("[", "")
                ?.replace("]", "")
        }
        if (mBinding.tvPokemonAuthor.text.isEmpty()){
            mBinding.tvPokemonAuthor.text = activity.resources.getString(R.string.no_authors)
        }

        if (isDeletable) {
            mBinding.btnCardAddInFavorite.text =
                activity.resources.getString(R.string.card_btn_favorite_remove)
        } else {
            mBinding.btnCardAddInFavorite.text =
                activity.resources.getString(R.string.card_btn_favorite_add)
        }
        mBinding.btnCardAddInFavorite.setOnClickListener {
            if (isDeletable) {
                Log.d("BRUH", "item ==== ${item.id}")
                onPokemonCardClickListener.onRemoveFromFavoriteClicked(
                    item = item,
                    position = absoluteAdapterPosition
                )
            } else {
                onPokemonCardClickListener.onAddToFavoriteClicked(
                    item = item,
                    position = absoluteAdapterPosition
                )
            }
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