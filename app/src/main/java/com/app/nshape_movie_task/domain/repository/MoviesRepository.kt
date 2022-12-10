package com.app.nshape_movie_task.domain.repository

import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.common.ApiResult
import com.app.nshape_movie_task.domain.entity.MoviesEntity

interface MoviesRepository {

  suspend fun getMovies(): ApiResult<List<MoviesEntity>, ApiFailure>
}