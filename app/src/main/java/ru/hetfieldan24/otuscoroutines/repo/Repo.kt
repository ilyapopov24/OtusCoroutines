package ru.hetfieldan24.otuscoroutines.repo

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.hetfieldan24.otuscoroutines.database.SongsTable

class Repo(application: Application) {
    private val remoteDataSource = RemoteDataSource.getInstance()
    private val dbDataSource = DBDataSource.getInstance(application)

    private val songsFromDB = dbDataSource.getAllSongs()

    suspend fun loadSongsFromRemote(): LiveData<List<SongsTable>> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.loadSongs()
        }
    }

    fun getSongsFromDB(): LiveData<List<SongsTable>> = songsFromDB

    suspend fun cacheSongs(songs: List<SongsTable>?) {
        songs?.let {
            withContext(Dispatchers.IO) {
                dbDataSource.clearDB()
                dbDataSource.insertSongs(songs)
            }
        }
    }
}