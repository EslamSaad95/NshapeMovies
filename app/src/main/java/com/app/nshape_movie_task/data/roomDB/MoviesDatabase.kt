package com.app.nshape_movie_task.data.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDatabaseEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
  abstract fun MoviesDao(): MoviesDao
}