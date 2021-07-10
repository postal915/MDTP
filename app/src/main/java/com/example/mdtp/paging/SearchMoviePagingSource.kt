package com.example.mdtp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mdtp.api.RetrofitInstance
import com.example.mdtp.model.movie.MovieDTO
import retrofit2.HttpException
import java.io.IOException

class SearchMoviePagingSource(private val searchQuery: String) : PagingSource<Int, MovieDTO>() {

    companion object {
        private const val MOVIE_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {

        val position = params.key ?: MOVIE_STARTING_PAGE_INDEX
        return try {
            val response =
                RetrofitInstance.api.getSearchMoviePaging(
                    page = position,
                    searchQuery = searchQuery
                )
            val foundFilms = response.results

            LoadResult.Page(
                data = foundFilms,
                prevKey = if (position == MOVIE_STARTING_PAGE_INDEX) null
                else position.minus(1),
                nextKey = if (foundFilms.isEmpty()) null
                else position.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}