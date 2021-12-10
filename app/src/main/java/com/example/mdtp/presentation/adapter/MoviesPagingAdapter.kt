package com.example.mdtp.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mdtp.R
import com.example.mdtp.model.movie.MovieDTO
import com.example.mdtp.presentation.detail.MovieDetailActivity
import com.example.mdtp.util.Constants
import com.example.mdtp.util.DiffUtilCallBack
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesPagingAdapter :
    PagingDataAdapter<MovieDTO, MoviesPagingAdapter.MoviesViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesPagingAdapter.MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesPagingAdapter.MoviesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bindMovie(movie)
        }
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val description: TextView = itemView.title_textView
        private val image: ImageView = itemView.imageView

        fun bindMovie(content: MovieDTO) {
            val urlMoviePoster = Constants.BASE_URL_Movie_Poster + content.posterPath

            description.text = content.title

            Glide.with(itemView)
                .load(urlMoviePoster)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .fallback(R.drawable.null_image)
                .into(image)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailActivity()::class.java)
                intent.putExtra("movieId", content.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}