package com.app.nshape_movie_task.domain.useCase

import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.domain.repository.MoviesDatabaseRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesDataBaseUseCase@Inject constructor(private val repo: MoviesDatabaseRepository) {

  suspend fun addTeam(movie: MoviesEntity) {
    repo.addMoviesToDB(movie)
  }

  fun getAllMovies() = flow { emit(repo.getAllFavMoviesFromDB()) }


  suspend fun removeFromTeams(movie: MoviesEntity) {
    repo.removeMoviesFromDB(movie)
  }
}