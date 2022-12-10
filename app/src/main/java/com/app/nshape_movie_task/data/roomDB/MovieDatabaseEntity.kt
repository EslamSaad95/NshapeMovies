package com.app.nshape_movie_task.data.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDatabaseEntity(

  @PrimaryKey()
  @ColumnInfo(name = "movieTitle")
  val movieTitle: String,

  @ColumnInfo(name = "movieReleaseDate")
  val movieReleaseDate: String?,

  @ColumnInfo(name = "moviePosterUrl")
  val moviePosterUrl: String? ,

  @ColumnInfo(name = "movieRating")
  val movieRating: Float?,

  @ColumnInfo(name = "MovieIsFav")
  val MovieIsFav: Boolean,

  )