package com.amg.task1.adapters

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amg.task1.ItemDetailActivity
import com.amg.task1.ItemListActivity
import com.amg.task1.Movie
import com.amg.task1.databinding.ListItemHorBinding

class MoviesAdapterHor(private val movies: List<Movie>, private val activity: ItemListActivity) :
    RecyclerView.Adapter<MoviesAdapterHor.ItemViewHolder>() {

    class ItemViewHolder(private val binding: ListItemHorBinding, activity: ItemListActivity) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var movie: Movie

        init {
            binding.root.setOnClickListener {
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    activity,
                    it,
                    "shared_element_container" // The transition name to be matched in Activity B.
                )
                activity.startActivity(Intent(activity, ItemDetailActivity::class.java).apply {

                    putExtra("movie", Bundle().apply {
                        putParcelable("movie", movie)
                    })
                }, options.toBundle())
            }
        }

        fun bind(movie: Movie) {
            binding.movie = movie
            this.movie = movie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemHorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), activity
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (movies.isNotEmpty()) holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}