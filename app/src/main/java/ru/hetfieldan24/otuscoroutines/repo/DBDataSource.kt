package ru.hetfieldan24.otuscoroutines.repo

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.hetfieldan24.otuscoroutines.database.SongsDao
import ru.hetfieldan24.otuscoroutines.database.SongsDatabase
import ru.hetfieldan24.otuscoroutines.database.SongsTable

class DBDataSource private constructor(application: Application) {
    private val dao: SongsDao

    init {
        val db: SongsDatabase = SongsDatabase.getInstance(application)
        dao = db.songsDao
    }

    fun getAllSongs(): LiveData<List<SongsTable>> {
        return dao.getAllSongs()
    }

    suspend fun insertSongs(songs: List<SongsTable>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(songs)
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
}