package ru.hetfieldan24.otuscoroutines.repo

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.hetfieldan24.otuscoroutines.database.SongsDao
import ru.hetfieldan24.otuscoroutines.database.SongsDatabase
import ru.hetfieldan24.otuscoroutines.database.SongsTable

class DBDataSource(application: Application): DataSource {
    private val dao: SongsDao

    init {
        val db: SongsDatabase = SongsDatabase.getInstance(application)
        dao = db.songsDao
    }

    override suspend fun loadSongs(): LiveData<List<SongsTable>> {
        return withContext(Dispatchers.IO) {
            val songs = dao.getAllSongs()
            Log.e("ADAPTER", "songs from DB Data Source: ${songs.value}")
            songs
        }
    }

    suspend fun insertSong(songTable: SongsTable) {
        withContext(Dispatchers.IO) {
            Log.e("ADAPTER", "insertSong: $songTable")
            dao.insert(songTable)
        }
    }

    suspend fun updateSong(songTable: SongsTable) {
        withContext(Dispatchers.IO) {
            dao.update(songTable)
        }
    }

    suspend fun clearDB() {
        withContext(Dispatchers.IO) {
            dao.clear()
        }
    }
    /*
    companion object {
        @Volatile
        private var INSTANCE: DBDataSource? = null

        fun getInstance(application: Application): DBDataSource {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = DBDataSource(application)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    */
}