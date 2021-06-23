package com.example.mdtp.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdtp.model.movie.DataDTO
import com.example.mdtp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(private val repository: Repository) : ViewModel() {

    val foundFilms: MutableLiveData<Response<DataDTO>> = MutableLiveData()

    fun getSearchMovie(searchQuery: String) {
        viewModelScope.launch {
            val response = repository.getSearchMovie(searchQuery)
            foundFilms.value = response
        }
    }
}