package com.app.nshape_movie_task.data.di

import com.app.nshape_movie_task.data.repositoryImp.MoviesDateBaseRepoImpl
import com.app.nshape_movie_task.data.repositoryImp.MoviesRepoImpl
import com.app.nshape_movie_task.domain.repository.MoviesDatabaseRepository
import com.app.nshape_movie_task.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

  @Singleton
  @Binds
  abstract fun provideTrendingMovie(repoImpl: MoviesRepoImpl): MoviesRepository

  @Singleton
  @Binds
  abstract fun provideDBRepo(repo: MoviesDateBaseRepoImpl): MoviesDatabaseRepository
}