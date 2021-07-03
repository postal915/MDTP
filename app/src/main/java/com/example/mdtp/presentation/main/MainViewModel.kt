package com.example.mdtp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mdtp.model.movie.DataDTO
import com.example.mdtp.model.movie.MovieDTO
import com.example.mdtp.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val movies: MutableLiveData<Response<DataDTO>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            val response = repository.getMovies()
            movies.value = response
        }
    }

    fun getMoviesPaging(): Pager<Int, MovieDTO> {
        return repository.getMoviesPaging()
    }
    fun getMoviesPagingFlow(): Flow<PagingData<MovieDTO>> {
        return repository.getMoviesPagingFlow().cachedIn(viewModelScope)
    }
}