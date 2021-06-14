package com.example.mdtp.api

import com.example.mdtp.model.detail.MovieDetailDTO
import com.example.mdtp.model.movie.ResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/discover/movie?api_key=1cc33b07c9aa5466f88834f7042c9258&language=en-US&sort_by=popularity.asc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
    suspend fun getMovies(): Response<ResultDTO>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "1cc33b07c9aa5466f88834f7042c9258",
        @Query("language") lang: String = "language=en-US"
    ): Response<MovieDetailDTO>
}