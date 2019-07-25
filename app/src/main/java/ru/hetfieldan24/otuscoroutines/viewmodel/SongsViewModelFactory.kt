package ru.hetfieldan24.otuscoroutines.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModel

class SongsViewModelFactory(
        private val application: Application) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(SongsViewModel::class.java))
        {
            return SongsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
