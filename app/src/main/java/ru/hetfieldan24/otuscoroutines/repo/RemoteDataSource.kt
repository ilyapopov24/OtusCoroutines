package ru.hetfieldan24.otuscoroutines.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import ru.hetfieldan24.otuscoroutines.model.SongsResponse
import ru.hetfieldan24.otuscoroutines.network.RetrofitService

class RemoteDataSource: DataSource {
    private var retrofitService: RetrofitService = RetrofitService.getInstance()

    override suspend fun loadSongs(): LiveData<List<SongsTable>>
    {
        val response = retrofitService.getJSONApi().getSongsResponse().await()
        return parseSongsResponse(response)
    }

    private fun parseSongsResponse(songsResponse: SongsResponse): LiveData<List<SongsTable>>
    {
        val songs = songsResponse.songs
        val liveData = MutableLiveData<List<SongsTable>>()

        songs?.let { songsList ->
            val parsedSongsList = List(songsList.size) { songIndex ->
                SongsTable(
                    songId = songIndex.toLong(),
                    songGenre = songsResponse.genre,
                    songName = songs[songIndex].songName,
                    imageUrl = songs[songIndex].imageUrl,
                    songSubGenre = songs[songIndex].subGenre?.name,
                    userName = songs[songIndex].userName
                )
            }
            liveData.postValue(parsedSongsList)
        }
        return liveData
    }

    companion object
    {
        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource
        {
            synchronized(this)
            {
                var instance = INSTANCE

                if (instance == null)
                {
                    instance = RemoteDataSource()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}