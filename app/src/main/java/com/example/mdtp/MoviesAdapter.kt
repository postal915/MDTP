package com.example.mdtp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdtp.model.movie.DataDTO
import com.example.mdtp.model.movie.MovieDTO
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var movieList = emptyList<MovieDTO>()

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        Log.d("Response size =", "${movieList.size}")
        holder.itemView.posterPath_textView.text =
            movieList[position].posterPath
        holder.itemView.title_textView.text = movieList[position].title
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(response: DataDTO) {
        movieList = response.results
        notifyDataSetChanged()
    }
}
