package ru.hetfieldan24.otuscoroutines.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import ru.hetfieldan24.otuscoroutines.R
import ru.hetfieldan24.otuscoroutines.TEST
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import ru.hetfieldan24.otuscoroutines.databinding.FragmentMainBinding
import ru.hetfieldan24.otuscoroutines.databinding.FragmentSongBinding
import ru.hetfieldan24.otuscoroutines.myLog
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModel
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModelFactory

class SongFragment: Fragment() {

    private lateinit var binding: FragmentSongBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song, container, false)

        val song: SongsTable = Gson().fromJson(requireArguments().getString(TEST), SongsTable::class.java)

        binding.songsTable = song

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val toolBar = (requireActivity() as AppCompatActivity).supportActionBar
        toolBar?.let {
            with(it) {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp)
            }
        }
    }
}