package com.example.mdtp.stevdza

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdtp.model.movie.ResultDTO

import com.example.mdtp.stevdza.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<ResultDTO>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getMovies()
            myResponse.value = response
        }
    }
}