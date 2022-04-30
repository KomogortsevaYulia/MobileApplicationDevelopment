package com.example.myapplication.presentation.main

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.usecase.GetBookUseCase
import com.example.myapplication.domain.usecase.SaveBookUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val saveBookUseCase: SaveBookUseCase,
    private val getBookUseCase: GetBookUseCase
) : ViewModel() {


}