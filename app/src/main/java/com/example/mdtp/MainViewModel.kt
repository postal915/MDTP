package com.example.mdtp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdtp.model.detail.MovieDetailDTO
import com.example.mdtp.model.movie.DataDTO

import com.example.mdtp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val movies: MutableLiveData<Response<DataDTO>> = MutableLiveData()
    val movieDetail: MutableLiveData<Response<MovieDetailDTO>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            val response = repository.getMovies()
            movies.value = response
        }
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val response = repository.getMovieDetail(movieId)
            movieDetail.value = response
        }
    }
}