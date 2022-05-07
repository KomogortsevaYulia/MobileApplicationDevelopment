package com.example.myapplication.presentation.favorite

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.app.App
import com.example.myapplication.databinding.FragmentFavoriteBinding
import com.example.myapplication.domain.model.Item
import com.example.myapplication.presentation.adapter.book.BookRecyclerViewAdapter
import com.example.myapplication.presentation.adapter.book.OnBookCardClickListener
import javax.inject.Inject

class FavoriteFragment : Fragment(), OnBookCardClickListener {

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

        mViewModel.getFavoriteBookList()
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        mViewModel.favoritePokemonListLiveData.observe(viewLifecycleOwner) {
            Log.d("book_list_favorite", "$it")
            mAdapter = BookRecyclerViewAdapter(it, requireActivity(), this, isDeletable = true)
            mBinding.rcvFavoritePokemon.adapter = mAdapter
            mAdapter.notifyDataSetChanged()

//            mViewModel.favoritePokemonListLiveData.observe(viewLifecycleOwner) { items ->
//                if (items != null) {
//                    mAdapter = BookRecyclerViewAdapter(items, requireActivity(), this@FavoriteFragment)
//                    mBinding.rcvFavoritePokemon.adapter = mAdapter
//                    mAdapter.notifyDataSetChanged()
//                }
//                /* if (it.items.isEmpty()) {
//                     mBinding.ivPokemonListEmpty.visibility = View.VISIBLE
//                     mBinding.rcvFavoritePokemon.visibility = View.GONE
//                 } else {
//                     mBinding.ivPokemonListEmpty.visibility = View.GONE
//                     mBinding.rcvFavoritePokemon.visibility = View.VISIBLE
//                 }
//                 mBinding.rcvFavoritePokemon.layoutManager?.onRestoreInstanceState(rcvState)*/
//            }
        }
    }

    override fun onAddToFavoriteClicked(item: Item, position: Int) {

    }

    override fun onRemoveFromFavoriteClicked(item: Item, position: Int) {
        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
        Log.d("BRUH", "before = ${mAdapter.getList().map { it.id }}")
        //rcvState = mBinding.rcvFavoritePokemon.layoutManager?.onSaveInstanceState()
        mViewModel.removeBookById(item.id)
        mAdapter.notifyItemRemoved(0)
        //mAdapter.notifyItemRangeChanged(position, mAdapter.getList().size)
        Log.d("BRUH", "after = ${mAdapter.getList().map { it.id }}")
    }
}