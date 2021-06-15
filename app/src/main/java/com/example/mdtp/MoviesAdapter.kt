package com.example.mdtp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mdtp.model.movie.MovieDTO
import com.example.mdtp.model.movie.ResultDTO
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
        if (movieList[position].posterPath != null) holder.itemView.posterPath_textView.text =
            movieList[position].posterPath.toString()
        holder.itemView.title_textView.text = movieList[position].title
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(response: ResultDTO) {
        movieList = response.results
        notifyDataSetChanged()
    }
}
