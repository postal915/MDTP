package com.example.mdtp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mdtp.api.RetrofitInstance
import com.example.mdtp.model.detail.MovieDetailDTO
import com.example.mdtp.model.movie.DataDTO
import com.example.mdtp.model.movie.MovieDTO
import com.example.mdtp.paging.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository {

    suspend fun getMovies(): Response<DataDTO> {
        return RetrofitInstance.api.getMovies()
    }

    suspend fun getMovieDetail(movieId: Int): Response<MovieDetailDTO> {
        return RetrofitInstance.api.getMovieDetail(movieId)
    }

    suspend fun getSearchMovie(searchQuery: String): Response<DataDTO> {
        return RetrofitInstance.api.getSearchMovie(searchQuery = searchQuery)
    }

    fun getMoviesPaging(): Pager<Int, MovieDTO> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = ::MoviesPagingSource
        )
    }

    fun getMoviesPagingFlow(): Flow<PagingData<MovieDTO>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = ::MoviesPagingSource
        ).flow
    }
}