package ru.hetfieldan24.otuscoroutines.view

import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import ru.hetfieldan24.otuscoroutines.GlideApp
import ru.hetfieldan24.otuscoroutines.database.SongsTable


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    GlideApp.with(context)
        .load(url)
        .into(this)
}

@BindingAdapter("onSongClick")
fun RelativeLayout.onSongClick(songTable: SongsTable) {
    setOnClickListener {

    }
}