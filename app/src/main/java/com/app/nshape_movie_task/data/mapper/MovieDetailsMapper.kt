package com.app.nshape_movie_task.data.mapper

import com.app.nshape_movie_task.data.network.Dto.MovieDetailsDto
import com.app.nshape_movie_task.domain.entity.MoviesEntity

fun MovieDetailsDto.toMovieDetailsEntity():MoviesEntity
{
  return MoviesEntity(this.id,this.posterPath,this.originalTitle,this.popularity.toFloat(),this.releaseDate,this
    .overview
  ,this.voteAverage.toFloat(),this.originalLanguage,false)
}