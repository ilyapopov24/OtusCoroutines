package ru.hetfieldan24.otuscoroutines.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModelFactory
import ru.hetfieldan24.otuscoroutines.R
import ru.hetfieldan24.otuscoroutines.database.SongsDatabase
import ru.hetfieldan24.otuscoroutines.databinding.FragmentMainBinding
import ru.hetfieldan24.otuscoroutines.repo.DBDataSource
import ru.hetfieldan24.otuscoroutines.repo.RemoteDataSource
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModel

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModelFactory: SongsViewModelFactory
    private lateinit var songsViewModel: SongsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val application = requireNotNull(this.activity).application
        viewModelFactory = SongsViewModelFactory(application)

        songsViewModel =
                ViewModelProviders.of(
                        this, viewModelFactory).get(SongsViewModel::class.java)

        binding.songsViewModel = songsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = SongsListAdapter(songsViewModel)
        binding.songsRecyclerView.adapter = adapter

        songsViewModel.songs.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.e("ADAPTER", "SUBMIT")
                adapter.submitList(it.toList())
                songsViewModel.cacheSongs(it.toList())
            }
        })
        songsViewModel.loadSongs()
        return binding.root
    }
}