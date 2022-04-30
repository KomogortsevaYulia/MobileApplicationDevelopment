package com.example.myapplication.presentation.favorite

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.app.App
import com.example.myapplication.databinding.FragmentFavoriteBinding
import com.example.myapplication.domain.model.Item
import com.example.myapplication.presentation.adapter.book.BookRecyclerViewAdapter
import com.example.myapplication.presentation.adapter.book.OnBookCardClickListener
import javax.inject.Inject

class FavoriteFragment : Fragment(){

    private val mBinding by viewBinding(FragmentFavoriteBinding::bind)

    @Inject
    lateinit var mViewModel: FavoriteViewModel

    private lateinit var mAdapter: BookRecyclerViewAdapter

    private var rcvState: Parcelable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()

        mViewModel.getFavoritePokemonList()
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        mViewModel.favoritePokemonListLiveData.observe(viewLifecycleOwner) {
           // mAdapter = BookRecyclerViewAdapter(it.items, requireActivity(), this)
            mBinding.rcvFavoritePokemon.adapter = mAdapter
            mAdapter.notifyDataSetChanged()

            if (it.items.isEmpty()) {
                mBinding.ivPokemonListEmpty.visibility = View.VISIBLE
                mBinding.rcvFavoritePokemon.visibility = View.GONE
            } else {
                mBinding.ivPokemonListEmpty.visibility = View.GONE
                mBinding.rcvFavoritePokemon.visibility = View.VISIBLE
            }
            mBinding.rcvFavoritePokemon.layoutManager?.onRestoreInstanceState(rcvState)
        }
    }

//    override fun onRemoveFromFavoriteClicked(item: Item, position: Int) {
//        rcvState = mBinding.rcvFavoritePokemon.layoutManager?.onSaveInstanceState()
//        item.id?.let { mViewModel.removeBookById(it) }
//        mAdapter.notifyItemRemoved(position)
//    }
}