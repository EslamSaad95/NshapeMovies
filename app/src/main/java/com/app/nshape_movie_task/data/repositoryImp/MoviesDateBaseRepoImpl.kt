package com.app.nshape_movie_task.data.repositoryImp

import com.app.nshape_movie_task.data.mapper.toMovieEntity
import com.app.nshape_movie_task.data.mapper.toMoviesDataBaseEntity
import com.app.nshape_movie_task.data.roomDB.MoviesDao
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.domain.repository.MoviesDatabaseRepository
import javax.inject.Inject

class MoviesDateBaseRepoImpl @Inject constructor(private val dbDao:MoviesDao):MoviesDatabaseRepository {

  override suspend fun addMoviesToDB(moviesEntity: MoviesEntity) {
    dbDao.insertTeam(moviesEntity.toMoviesDataBaseEntity())
  }

  override suspend fun removeMoviesFromDB(moviesEntity: MoviesEntity) {
    dbDao.delete(moviesEntity.toMoviesDataBaseEntity())
  }

  override suspend fun getAllFavMoviesFromDB(): List<MoviesEntity> {
   return dbDao.getAllMovies().toMovieEntity()
  }


}