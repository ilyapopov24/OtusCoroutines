package ru.hetfieldan24.otuscoroutines.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SongsDao
{
    @Insert
    fun insert(songs: SongsTable)

    @Update
    fun update(songs: SongsTable)

    @Query("SELECT * from songs_table WHERE song_name = :name")
    fun getSongByName(name: String): SongsTable?

    @Query("DELETE FROM songs_table")
    fun clear()

    @Query("SELECT * FROM songs_table")
    fun getAllSongs(): LiveData<List<SongsTable>>

    @Query("SELECT * from songs_table WHERE songId = :songId")
    fun getSongById(songId: Long): LiveData<SongsTable>
}
