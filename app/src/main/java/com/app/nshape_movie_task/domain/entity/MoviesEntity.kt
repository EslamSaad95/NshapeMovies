package com.app.nshape_movie_task.domain.entity

data class MoviesEntity(
  val moviePoster:String?="",
  val movieName:String,
  val rating:Float,
  val releaseDate:String,
  var addToFavourite:Boolean
)