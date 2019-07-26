package ru.hetfieldan24.otuscoroutines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import ru.hetfieldan24.otuscoroutines.repo.Repo

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var repo: Repo = Repo(application)

    private val _songsFromRemote = MutableLiveData<List<SongsTable>>()
    val songsFromRemote: LiveData<List<SongsTable>>
        get() = _songsFromRemote

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _onSongClicked = MutableLiveData<SongsTable>()
    val onSongClicked: LiveData<SongsTable>
        get() = _onSongClicked

    val songsFromDB = repo.getSongsFromDB()

    fun loadSongs() {
        _isLoading.value = true
        uiScope.launch {
            _songsFromRemote.value =  repo.loadSongsFromRemote().value
        }
    }

    fun cacheSongs(songs: List<SongsTable>) {
        _isLoading.value = false
        uiScope.launch {
            repo.cacheSongs(songs)
        }
    }

    fun onSongClick(songsTable: SongsTable) {
        _onSongClicked.value = songsTable
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
