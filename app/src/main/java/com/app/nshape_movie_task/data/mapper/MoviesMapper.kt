package com.app.nshape_movie_task.data.mapper

import com.app.nshape_movie_task.data.network.Dto.MoviesDto
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