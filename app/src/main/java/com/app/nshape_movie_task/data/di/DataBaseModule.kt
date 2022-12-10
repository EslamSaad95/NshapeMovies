package com.app.nshape_movie_task.data.di

import android.content.Context
import androidx.room.Room
import com.app.nshape_movie_task.data.roomDB.MoviesDao
import com.app.nshape_movie_task.data.roomDB.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides
  fun provideChannelDao(appDatabase: MoviesDatabase): MoviesDao {
    return appDatabase.MoviesDao()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
    return Room.databaseBuilder(
      appContext,
      MoviesDatabase::class.java, "movies"
    ).build()
  }
}