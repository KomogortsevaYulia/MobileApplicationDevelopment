package com.example.myapplication.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.app.App
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.domain.model.Item
import com.example.myapplication.presentation.adapter.book.BookRecyclerViewAdapter
import com.example.myapplication.presentation.adapter.book.OnBookCardClickListener
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment(), OnBookCardClickListener {

    @Inject
    lateinit var mViewModel: SearchViewModel

    private val mBinding by viewBinding(FragmentSearchBinding::bind)

    private lateinit var mAdapter: BookRecyclerViewAdapter

    private lateinit var subscribe: Disposable

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initViews() {

    }

    private fun initListeners() {
//        mBinding.searchCardPokemon.btnCardAddInFavorite.setOnClickListener {
//            if (currentPokemon != null) {
//                favoriteViewModel.addBookToFavorite(item = currentPokemon!!)
//            }
//        }
    }

    private fun initObservers() {
        subscribe = RxTextView.textChanges(mBinding.etSearchPokemonName)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mViewModel.getBookByName(it.toString().lowercase())
            }, {
                Log.d("book_list_search", "${it.message}")
            })

        mViewModel.bookLiveData.observe(viewLifecycleOwner) {
            if (it != null ) {
                mAdapter = BookRecyclerViewAdapter(it.items, requireActivity(), this@SearchFragment)
                mBinding.rcvSearchBooks.adapter = mAdapter
                mAdapter.notifyDataSetChanged()
            }
//            else if (mBinding.etSearchPokemonName.text.toString().isEmpty()) {
//                mBinding.textInputLayoutSearchPokemonName.error = ""
//                mBinding.searchCardLayout.visibility = View.INVISIBLE
//                mBinding.ivSearchNothingFound.visibility = View.VISIBLE
//            } else if (mBinding.etSearchPokemonName.text.toString() != currentPokemon?.volumeInfo.title) {
//                mBinding.searchCardLayout.visibility = View.INVISIBLE
//                mBinding.ivSearchNothingFound.visibility = View.VISIBLE
//                mBinding.textInputLayoutSearchPokemonName.error = getString(R.string.search_not_found)
//            }
        }
    }

//    private fun bindCardView(item: Item) {
//        currentPokemon = item
//
//        mBinding.textInputLayoutSearchPokemonName.error = ""
//        mBinding.ivSearchNothingFound.visibility = View.INVISIBLE
//        mBinding.searchCardLayout.visibility = View.VISIBLE
//
//        mBinding.searchCardPokemon.tvPokemonName.text = item.volumeInfo?.title.replaceFirstChar { it.uppercase() }
//
//
//
//        loadPokemonImage(item.volumeInfo.imageLinks.thumbnail)
//    }
//
//    private fun loadPokemonImage(url: String) {
//        Glide
//            .with(requireActivity())
//            .load(url)
//            .into(mBinding.searchCardPokemon.ivPokemonImage)
//    }

    override fun onDestroyView() {
        subscribe.dispose()
        mViewModel.bookLiveData.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }

    override fun onAddToFavoriteClicked(item: Item, position: Int) {
        mViewModel.addBookToFavorite(item)
        mAdapter.notifyItemRemoved(position)
    }

    override fun onRemoveFromFavoriteClicked(item: Item, position: Int) {

    }
}