package com.app.nshape_movie_task.domain.repository

import com.app.nshape_movie_task.domain.entity.MoviesEntity

interface MoviesDatabaseRepository {


    suspend fun addMoviesToDB(moviesEntity: MoviesEntity)

    suspend fun removeMoviesFromDB(moviesEntity: MoviesEntity)

    suspend fun getAllFavMoviesFromDB(): List<MoviesEntity>

}