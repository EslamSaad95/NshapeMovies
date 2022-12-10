package com.app.nshape_movie_task.data.mapper

import com.app.nshape_movie_task.data.network.Dto.MoviesDto
import com.app.nshape_movie_task.data.roomDB.MovieDatabaseEntity
import com.app.nshape_movie_task.domain.entity.MoviesEntity

fun List<MoviesDto.MoviesList>.toMoviesEntity(): List<MoviesEntity> {
  return this.map {
    MoviesEntity(
      it.posterPath,
      it.originalTitle,
      it.voteAverage.toFloat(),
      it.releaseDate,
      false
    )
  }
}

fun MoviesEntity.toMoviesDataBaseEntity(): MovieDatabaseEntity {
  return MovieDatabaseEntity(this.movieName,this.releaseDate,this.moviePoster.toString(),this.rating,this
    .addToFavourite)


}


fun List<MovieDatabaseEntity>.toMovieEntity(): List<MoviesEntity> {
  return this.map {
MoviesEntity(it.moviePosterUrl,it.movieTitle,it.movieRating,it.movieReleaseDate,it.MovieIsFav)
  }
}