package com.example.mdtp.api

import com.example.mdtp.model.detail.MovieDetailDTO
import com.example.mdtp.model.movie.DataDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = "1cc33b07c9aa5466f88834f7042c9258",
        @Query("language") lang: String = "en-US",
        @Query("sort_by") sort_by: String = "popularity.desc"
    ): Response<DataDTO>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "1cc33b07c9aa5466f88834f7042c9258",
        @Query("language") lang: String = "en-US"
    ): Response<MovieDetailDTO>

    @GET("3/search/movie")
    suspend fun getSearchMovie(
        @Query("api_key") apiKey: String = "1cc33b07c9aa5466f88834f7042c9258",
        @Query("language") lang: String = "en-US",
        @Query("query") searchQuery: String
    ): Response<DataDTO>
}