package com.app.nshape_movie_task.domain.useCase

import com.app.nshape_movie_task.data.roomDB.MoviesDao
import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.common.ApiResult
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.domain.repository.MoviesDatabaseRepository
import com.app.nshape_movie_task.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
  private val repository: MoviesRepository,
  private val dbRepo: MoviesDatabaseRepository
) {

  suspend fun getTrendingMovies() = checkOnFavItems(repository.getMovies())

  private fun checkOnFavItems(moviesList: ApiResult<List<MoviesEntity>, ApiFailure>) = flow {

    val favNewsList = dbRepo.getAllFavMoviesFromDB()
    if (favNewsList.isNotEmpty()) {
      favNewsList.forEach { favItems ->
        moviesList.value?.forEach { moviesItem ->
          if (favItems.movieName == moviesItem.movieName)
            moviesItem.addToFavourite = true
        }
      }
    }
    emit(moviesList)

  }
}