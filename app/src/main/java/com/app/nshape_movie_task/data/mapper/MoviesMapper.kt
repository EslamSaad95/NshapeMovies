package com.app.nshape_movie_task.data.mapper

import com.app.nshape_movie_task.data.network.Dto.MoviesDto
import com.app.nshape_movie_task.data.roomDB.MovieDatabaseEntity
import com.app.nshape_movie_task.domain.entity.MoviesEntity

fun List<MoviesDto.MoviesList>.toMoviesEntity(): List<MoviesEntity> {
  return this.map {
    MoviesEntity(
      it.id,
      it.posterPath,
      it.originalTitle,
      it.popularity.toFloat(),
      it.releaseDate,
      it.overview,
      it.voteAverage.toFloat(),
      it.originalLanguage,
      false
    )
  }
}

fun MoviesEntity.toMoviesDataBaseEntity(): MovieDatabaseEntity {
  return MovieDatabaseEntity(this.movieId,this.movieName,this.releaseDate,this.moviePoster.toString(),this.rating,
    this.movieOverview,this.movieVoteAverage,this.movieLanguage,
    this.addToFavourite)


}


fun List<MovieDatabaseEntity>.toMovieEntity(): List<MoviesEntity> {
  return this.map {
MoviesEntity(it.movieId,it.moviePosterUrl,it.movieTitle,it.movieRating,it.movieReleaseDate,it
  .MovieOverview,it.movieAverageVote,it.movieLanguage,false)
  }
}