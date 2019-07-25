package ru.hetfieldan24.otuscoroutines.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs_table")
data class SongsTable
(
    @PrimaryKey(autoGenerate = true)
    var songId: Long = 0L,

    @ColumnInfo(name = "song_name")
    var songName: String? = "",

    @ColumnInfo(name = "user_name")
    var userName: String? = "",

    @ColumnInfo(name = "image_url")
    var imageUrl: String? = "",

    @ColumnInfo(name = "song_genre")
    var songGenre: String? = "",

    @ColumnInfo(name = "song_sub_genre")
    var songSubGenre: String? = ""
)
