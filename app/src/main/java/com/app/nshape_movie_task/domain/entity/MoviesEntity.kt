package com.app.nshape_movie_task.domain.entity

data class MoviesEntity(
  val movieId:Int,
  val moviePoster:String?="",
  val movieName:String,
  val rating:Float,
  val releaseDate:String,
  val movieOverview:String,
  val movieVoteAverage:Float,
  val movieLanguage:String,
  var addToFavourite:Boolean,

)