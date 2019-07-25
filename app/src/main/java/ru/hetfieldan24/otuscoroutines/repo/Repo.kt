package ru.hetfieldan24.otuscoroutines.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.hetfieldan24.otuscoroutines.database.SongsTable

class Repo(application: Application): DataSource {
    private val remoteDataSource = RemoteDataSource.getInstance()
    private val dbDataSource = DBDataSource(application)
    private val songs: LiveData<List<SongsTable>>

    init {
        songs = dbDataSource.loadSongs()
    }

    override suspend fun loadSongs(): LiveData<List<SongsTable>> {
        return withContext(Dispatchers.IO)
        {
            remoteDataSource.loadSongs()
        }
    }

    suspend fun cacheSongs(songs: List<SongsTable>?) {
        Log.e("ADAPTER", "cacheSongs: $songs")

        songs?.let {
            clearDB()
            songs.forEach { song ->
                insertSongToDB(song)
            }
        }
        val songsFromDB = dbDataSource.loadSongs()
        Log.e("ADAPTER", "songsFromDB: ${songsFromDB.value.toString()}")
    }

    private suspend fun insertSongToDB(song: SongsTable) {
        withContext(Dispatchers.IO) {
            dbDataSource.insertSong(song)
        }
    }

    private suspend fun clearDB() {
        withContext(Dispatchers.IO) {
            dbDataSource.clearDB()
        }
    }
}