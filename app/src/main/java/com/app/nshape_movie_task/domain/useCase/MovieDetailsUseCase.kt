package com.app.nshape_movie_task.domain.useCase

import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.common.ApiResult
import com.app.nshape_movie_task.domain.entity.MovieDetailsEntity
import com.app.nshape_movie_task.domain.repository.MovieDetailsRepository
import com.app.nshape_movie_task.domain.repository.MoviesDatabaseRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
  private val repository: MovieDetailsRepository,
  private val dbRepo: MoviesDatabaseRepository
) {

  suspend fun getMovieDetails(id: Int) = checkOnFavItem(repository.getMovieDetails(id))

  private fun checkOnFavItem(movieObj: ApiResult<MovieDetailsEntity, ApiFailure>) = flow {

    val favNewsList = dbRepo.getAllFavMoviesFromDB()
    if (favNewsList.isNotEmpty()) {
      favNewsList.forEach { favItems ->
        movieObj?.value?.let {
          if (it.movieName == favItems.movieName)
            it.addToFavourite = true
        }
      }
    }
    emit(movieObj)

  }
}