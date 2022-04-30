package com.example.myapplication.presentation.favorite

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Item
import com.example.myapplication.domain.usecase.GetBookUseCase
import com.example.myapplication.domain.usecase.RemoveBookUseCase
import com.example.myapplication.domain.usecase.SaveBookUseCase
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val removeBookUseCase: RemoveBookUseCase,
    private val saveBookUseCase: SaveBookUseCase
) : ViewModel() {

    private val _favoritePokemonListLiveData: MutableLiveData<Book> = MutableLiveData()
    val favoritePokemonListLiveData: LiveData<Book> = _favoritePokemonListLiveData

    @SuppressLint("CheckResult")
    fun getFavoritePokemonList() {
        getBookUseCase.getFavoriteBookList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
            })
    }



    @SuppressLint("CheckResult")
    fun removeBookById(id: String) {
        Single.just(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                removeBookUseCase.removeBookById(it)
            }, {

            })
    }

    private fun getFavoriteBookListObserver(): Single<Book> {
        return object : Single<Book>() {

            override fun subscribeActual(observer: SingleObserver<in Book>) {
                TODO("Not yet implemented")
            }
        }
    }
}