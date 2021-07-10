package com.example.mdtp.util

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.mdtp.model.movie.MovieDTO

class DiffUtilCallBack : DiffUtil.ItemCallback<MovieDTO>() {

    override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
        return oldItem == newItem
    }
}