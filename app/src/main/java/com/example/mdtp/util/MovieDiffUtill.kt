package com.example.mdtp.util

import androidx.recyclerview.widget.DiffUtil
import com.example.mdtp.model.movie.MovieDTO

class MyDiffUtil(
    private var oldMovieList: List<MovieDTO>,
    private var newMovieList: List<MovieDTO>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldMovieList.size
    }

    override fun getNewListSize(): Int {
        return newMovieList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList[oldItemPosition].id == newMovieList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldMovieList[oldItemPosition].id != newMovieList[newItemPosition].id -> {
                false
            }
            oldMovieList[oldItemPosition].title != newMovieList[newItemPosition].title -> {
                false
            }
            oldMovieList[oldItemPosition].posterPath != newMovieList[newItemPosition].posterPath -> {
                false
            }
            oldMovieList[oldItemPosition].releaseDate != newMovieList[newItemPosition].releaseDate -> {
                false
            }
            oldMovieList[oldItemPosition].overview != newMovieList[newItemPosition].overview -> {
                false
            }
            else -> true
        }
    }
}