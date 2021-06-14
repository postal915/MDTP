package com.example.mdtp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mdtp.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getMovies()
        viewModel.movies.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.results?.forEach {
                    Log.d("Response", "Movies")
                    Log.d("Response", "id = ${it.id}")
                    Log.d("Response", "title = ${it.title}")
                    Log.d("Response", "posterPath = ${it.posterPath}")
                    Log.d("Response", "----------")
                }
            } else {
                Log.d("Response error", response.errorBody().toString())
                Log.d("Response code", response.code().toString())
            }
        })

        viewModel.getMovieDetail(840445)
        viewModel.movieDetail.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Response", "Movie Detail")
                Log.d("Response", "id = ${response.body()?.id}")
                Log.d("Response", "title = ${response.body()?.title}")
                Log.d("Response", "overview = ${response.body()?.overview}")
                Log.d("Response", "releaseDate = ${response.body()?.releaseDate}")
                Log.d("Response", "posterPath = ${response.body()?.posterPath}")
                Log.d("Response", "----------")
            } else {
                Log.d("Response error", response.errorBody().toString())
                Log.d("Response code", response.code().toString())
            }
        })
    }
}