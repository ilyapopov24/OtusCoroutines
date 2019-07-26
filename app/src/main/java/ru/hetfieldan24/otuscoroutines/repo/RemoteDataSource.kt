package ru.hetfieldan24.otuscoroutines.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import ru.hetfieldan24.otuscoroutines.model.SongsResponse
import ru.hetfieldan24.otuscoroutines.network.RetrofitService

class RemoteDataSource private constructor(){
    private var retrofitService: RetrofitService = RetrofitService.getInstance()

    suspend fun loadSongs(): LiveData<List<SongsTable>>
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
                    songGenre = "Жанр: " + songsResponse.genre,
                    songName = "Песня: " + songs[songIndex].songName,
                    imageUrl = songs[songIndex].imageUrl,
                    songSubGenre = "Поджанр: " + songs[songIndex].subGenre?.name,
                    userName = "Загружено пользователем " + songs[songIndex].userName
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