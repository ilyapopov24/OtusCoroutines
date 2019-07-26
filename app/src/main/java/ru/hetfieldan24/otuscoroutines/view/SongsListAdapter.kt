package ru.hetfieldan24.otuscoroutines.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.hetfieldan24.otuscoroutines.database.SongsTable
import ru.hetfieldan24.otuscoroutines.databinding.ItemSongsListBinding
import ru.hetfieldan24.otuscoroutines.viewmodel.SongsViewModel

class SongsListAdapter(private val songsViewModel: SongsViewModel):
        ListAdapter<SongsTable, SongsListAdapter.ViewHolder>(
            SongsTableDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songsViewModel, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemSongsListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(mySongsViewModel: SongsViewModel, mySongsTable: SongsTable) {
            with(binding) {
                songsTable = mySongsTable
                songsViewModel = mySongsViewModel

                executePendingBindings()
            }
        }

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSongsListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

class SongsTableDiffCallback : DiffUtil.ItemCallback<SongsTable>() {

    override fun areItemsTheSame(oldItem: SongsTable, newItem: SongsTable): Boolean {
        return oldItem.songId == newItem.songId
    }

    override fun areContentsTheSame(oldItem: SongsTable, newItem: SongsTable): Boolean {
        return oldItem == newItem
    }
}

class SongIconClickListener(val clickListener: (view: View, SongsTable: SongsTable) -> Unit) {
    fun onClick(view: View, SongsTable: SongsTable) = clickListener(view, SongsTable)
}
