package ru.hetfieldan24.otuscoroutines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import ru.hetfieldan24.otuscoroutines.R
import ru.hetfieldan24.otuscoroutines.TEST
import ru.hetfieldan24.otuscoroutines.databinding.FragmentMainBinding
import ru.hetfieldan24.otuscoroutines.myLog
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModel
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModelFactory

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

        songsViewModel.songsFromDB.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.toList())
            }
        })

        songsViewModel.songsFromRemote.observe(viewLifecycleOwner, Observer {
            it?.let {
                songsViewModel.cacheSongs(it.toList())
            }
        })

        songsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.refreshButtonDefault.visibility = View.GONE
                binding.refreshButtonLoading.visibility = View.VISIBLE
            } else {
                binding.refreshButtonDefault.visibility = View.VISIBLE
                binding.refreshButtonLoading.visibility = View.GONE
            }
        })

        songsViewModel.onSongClicked.observe(viewLifecycleOwner, Observer {
            it?.let {
                val fragment = SongFragment()
                val args = Bundle()
                args.putString(TEST, Gson().toJson(it))
                fragment.arguments = args

                requireActivity().supportFragmentManager.commit {
                    add(R.id.container, fragment)
                    hide(this@MainFragment)
                    addToBackStack(null)
                }
            }
        })

        return binding.root
    }
}