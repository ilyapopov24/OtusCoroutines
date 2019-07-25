package ru.hetfieldan24.otuscoroutines.view

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.BindingAdapter
import ru.hetfieldan24.otuscoroutines.R
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import ru.hetfieldan24.otuscoroutines.GlideApp


@BindingAdapter("onSongClick")
fun View.onSongClick(SongsTable: SongsTable?) {
    SongsTable?.let {
        setOnClickListener {
            Toast.makeText(context, SongsTable.songName, Toast.LENGTH_LONG).show()
        }
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    GlideApp.with(context)
        .load(url)
        .into(this)
}