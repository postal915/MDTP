package com.example.mdtp.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdtp.model.detail.MovieDetailDTO
import com.example.mdtp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel(private val repository: Repository) : ViewModel() {

    val movieDetail: MutableLiveData<Response<MovieDetailDTO>> = MutableLiveData()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val response = repository.getMovieDetail(movieId)
            movieDetail.value = response
        }
    }
}