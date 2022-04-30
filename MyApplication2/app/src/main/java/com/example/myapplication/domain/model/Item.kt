package com.example.myapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item_table")

@TypeConverters(
    VolumeInfoConverter::class
)
data class Item(
    @PrimaryKey @SerializedName("id") var id: String,
    @SerializedName("volumeInfo") var volumeInfo: VolumeInfo? = VolumeInfo(),
)