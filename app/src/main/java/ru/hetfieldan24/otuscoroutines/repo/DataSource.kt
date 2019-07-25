package ru.hetfieldan24.otuscoroutines.repo

import androidx.lifecycle.LiveData
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import ru.hetfieldan24.otuscoroutines.model.SongsResponse

interface DataSource {
    suspend fun loadSongs(): LiveData<List<SongsTable>>
}