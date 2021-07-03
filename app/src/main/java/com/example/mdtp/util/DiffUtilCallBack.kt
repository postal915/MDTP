package com.example.mdtp.util

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.mdtp.model.movie.MovieDTO

class DiffUtilCallBack : DiffUtil.ItemCallback<MovieDTO>() {

    override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
        Log.d("MyLog", "areItemsTheSame ${oldItem == newItem}")
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
        Log.d("MyLog", "areContentsTheSame ${oldItem == newItem}")
        return oldItem == newItem
    }
}