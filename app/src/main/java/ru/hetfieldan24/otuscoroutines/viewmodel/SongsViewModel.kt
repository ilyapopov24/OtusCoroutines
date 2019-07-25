package ru.hetfieldan24.otuscoroutines.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import ru.hetfieldan24.otuscoroutines.repo.DBDataSource
import ru.hetfieldan24.otuscoroutines.repo.Repo

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var repo: Repo = Repo(application)
    private val _songs = MutableLiveData<List<SongsTable>>()
    val songs: LiveData<List<SongsTable>>
        get() = _songs

    fun loadSongs() {
        uiScope.launch {
            Log.e("ADAPTER", "in VM repo songs: ${repo.loadSongs().value.toString()}")
            _songs.value = repo.loadSongs().value
        }
    }

    fun cacheSongs(songs: List<SongsTable>)
    {
        uiScope.launch {
            repo.cacheSongs(songs)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
