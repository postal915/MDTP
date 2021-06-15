package com.example.mdtp.repository

import com.example.mdtp.model.detail.MovieDetailDTO
import com.example.mdtp.model.movie.DataDTO
import com.example.mdtp.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getMovies(): Response<DataDTO> {
        return RetrofitInstance.api.getMovies()
    }

    suspend fun getMovieDetail(movieId: Int):Response<MovieDetailDTO>{
        return RetrofitInstance.api.getMovieDetail(movieId)
    }
}