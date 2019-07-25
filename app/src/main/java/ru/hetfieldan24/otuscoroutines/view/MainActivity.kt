package ru.hetfieldan24.otuscoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import ru.hetfieldan24.otuscoroutines.R
import ru.hetfieldan24.otuscoroutines.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.hetfieldan24.otuscoroutines.model.SongsResponse
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModel
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModelFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.container, MainFragment())
            addToBackStack(null)
        }
    }
}
