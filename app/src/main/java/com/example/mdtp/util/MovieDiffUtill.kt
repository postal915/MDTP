package com.example.mdtp.util

import android.util.Log
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
        Log.d(
            "MyLog",
            "areItemsTheSame ${oldMovieList[oldItemPosition] == newMovieList[newItemPosition]}"
        )
        return oldMovieList[oldItemPosition] == newMovieList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.d(
            "MyLog",
            "areItemsTheSame ${oldMovieList[oldItemPosition] != newMovieList[newItemPosition]}"
        )
        return oldMovieList[oldItemPosition] != newMovieList[newItemPosition]
    }
}