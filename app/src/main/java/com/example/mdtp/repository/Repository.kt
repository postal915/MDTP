package com.example.mdtp.stevdza.repository

import com.example.mdtp.model.movie.ResultDTO
import com.example.mdtp.stevdza.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getMovies(): Response<ResultDTO> {
        return RetrofitInstance.api.getMovies()
    }
}