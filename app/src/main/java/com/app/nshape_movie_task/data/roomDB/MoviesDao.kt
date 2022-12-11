package com.app.nshape_movie_task.data.roomDB

import androidx.room.*

@Dao
interface MoviesDao {

  @Query("SELECT * FROM moviedatabaseentity")
  suspend fun getAllMovies(): List<MovieDatabaseEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTeam(vararg movies: MovieDatabaseEntity)

  @Delete
  suspend fun delete(movie: MovieDatabaseEntity)
}