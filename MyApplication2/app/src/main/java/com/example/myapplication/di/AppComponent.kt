package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.presentation.favorite.FavoriteFragment
import com.example.myapplication.presentation.main.MainActivity
import com.example.myapplication.presentation.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(favoriteFragment: FavoriteFragment)
}