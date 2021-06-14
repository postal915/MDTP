package com.example.mdtp.repository

import com.example.mdtp.model.detail.MovieDetail
import com.example.mdtp.model.movie.ResultDTO
import com.example.mdtp.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getMovies(): Response<ResultDTO> {
        return RetrofitInstance.api.getMovies()
    }
}