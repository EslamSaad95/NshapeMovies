package com.app.nshape_movie_task.domain.useCase

import com.app.nshape_movie_task.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val repository: MoviesRepository) {

  suspend fun getTrendingMovies() = flow {
    emit(repository.getMovies())
  }
}