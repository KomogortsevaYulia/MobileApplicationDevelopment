package com.example.myapplication.presentation.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import com.example.myapplication.domain.usecase.GetBookUseCase
import com.example.myapplication.domain.usecase.SaveBookUseCase
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val saveBookUseCase: SaveBookUseCase
) : ViewModel() {

    private val _bookLiveData: MutableLiveData<Book> = MutableLiveData()
    val bookLiveData: LiveData<Book> = _bookLiveData

    @SuppressLint("CheckResult")
    fun getBookByName(name: String) {
        getBookUseCase.getBookByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("book_list_search", "${it}")
                _bookLiveData.value = it
            }, {
                Log.d("book_list_search", "${it.message}")
            })
    }

    fun addBookToFavorite(item: Item) {
        val disposable = Single.just(item)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                saveBookUseCase.saveBook(item)
            }, {

            })
    }
}