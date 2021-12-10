package com.example.mdtp.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mdtp.R
import com.example.mdtp.model.movie.DataDTO
import com.example.mdtp.model.movie.MovieDTO
import com.example.mdtp.presentation.detail.MovieDetailActivity
import com.example.mdtp.util.Constants.Companion.BASE_URL_Movie_Poster
import com.example.mdtp.util.MyDiffUtil
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(private val context: Context) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var oldMovieList = emptyList<MovieDTO>()

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        val urlMoviePoster = BASE_URL_Movie_Poster + oldMovieList[position].posterPath
        Glide.with(context)
            .load(urlMoviePoster)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .fallback(R.drawable.null_image)
            .into(holder.itemView.imageView)

        holder.itemView.title_textView.text = oldMovieList[position].title
        holder.itemView.itemRowLayout.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity()::class.java)
            intent.putExtra("movieId", oldMovieList[position].id)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return oldMovieList.size
    }

    fun setData(newMovieList: List<MovieDTO>) {
        val diffUtil = MyDiffUtil(oldMovieList, newMovieList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldMovieList = newMovieList
        diffResult.dispatchUpdatesTo(this)
    }
}