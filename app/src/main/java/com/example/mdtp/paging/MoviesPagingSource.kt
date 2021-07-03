package com.example.mdtp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mdtp.api.RetrofitInstance
import com.example.mdtp.model.movie.MovieDTO
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource : PagingSource<Int, MovieDTO>() {

    companion object {
        private const val MOVIES_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
        val position = params.key ?: MOVIES_STARTING_PAGE_INDEX
        return try {
            val response = RetrofitInstance.api.getMoviesPaging(page = position)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == MOVIES_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}