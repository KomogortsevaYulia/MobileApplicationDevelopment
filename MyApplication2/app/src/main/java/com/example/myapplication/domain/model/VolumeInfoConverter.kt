package com.example.myapplication.domain.model

import androidx.room.TypeConverter

class VolumeInfoConverter {

    @TypeConverter
    fun fromVolumeInfo(volumeInfo: VolumeInfo): String {
        return volumeInfo.title + "%" +
                volumeInfo.subtitle + "%" +
                volumeInfo.authors + "%" +
                volumeInfo.description + "%" +
                volumeInfo.categories + "%" +
                volumeInfo.imageLinks?.smallThumbnail
    }

    @TypeConverter
    fun toVolumeInfo(volumeInfoString: String): VolumeInfo {
        val list = volumeInfoString.split("%")
        return VolumeInfo(
            title = list[0],
            subtitle = list[1],
            authors = list[2].split(","),
            description = list[3],
            categories = list[4].split(","),
            imageLinks = ImageLinks(
                smallThumbnail = list[5]
            )
        )
    }
}