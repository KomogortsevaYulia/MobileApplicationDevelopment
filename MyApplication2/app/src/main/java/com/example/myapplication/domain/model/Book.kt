package com.example.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
@Entity
@TypeConverters(
    ItemConverter::class,
    VolumeInfoConverter::class
)
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("items") var items: List<Item> = emptyList()
)