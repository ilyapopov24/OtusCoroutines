package ru.hetfieldan24.otuscoroutines.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.commit
import ru.hetfieldan24.otuscoroutines.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.container, MainFragment())
        }

        setSupportActionBar(findViewById<Toolbar>(R.id.toolBar))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
