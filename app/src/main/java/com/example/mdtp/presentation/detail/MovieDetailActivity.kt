package com.example.mdtp.presentation.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mdtp.R
import com.example.mdtp.repository.Repository
import com.example.mdtp.util.Constants.Companion.BASE_URL_Movie_Poster
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra("movieId", 0)

        val repository = Repository()
        val viewModelFactory = MovieDetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        viewModel.getMovieDetail(movieId)
        viewModel.movieDetail.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let {

                    val urlMoviePoster = BASE_URL_Movie_Poster + it.posterPath
                    Glide.with(this)
                        .load(urlMoviePoster)
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .fallback(R.drawable.null_image)
                        .into(moviePoster_IV)

                    movieTitle_TV.text = it.title
                    movieReleaseDate_TV.text = it.releaseDate
                    movieOverview_TV.text = it.overview
                }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_LONG).show()
            }
        })
    }
}