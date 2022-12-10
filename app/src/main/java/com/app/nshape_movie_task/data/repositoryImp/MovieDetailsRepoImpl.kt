package com.app.nshape_movie_task.data.repositoryImp

import com.app.nshape_movie_task.data.common.map
import com.app.nshape_movie_task.data.mapper.toMovieDetailsEntity
import com.app.nshape_movie_task.data.mapper.toMoviesEntity
import com.app.nshape_movie_task.data.network.ApiService
import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.common.ApiResult
import com.app.nshape_movie_task.domain.entity.GeneralErrorDto
import com.app.nshape_movie_task.domain.entity.MovieDetailsEntity
import com.app.nshape_movie_task.domain.repository.MovieDetailsRepository
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor (private val apiService:ApiService)
  :MovieDetailsRepository {

  override suspend fun getMovieDetails(id: Int): ApiResult<MovieDetailsEntity, ApiFailure> {
    try {
      val response = apiService.getMovieDetails(id)
      if (response.isSuccessful)
        return ApiResult(value = response.body()?.toMovieDetailsEntity())
      else {
        val error = Gson().fromJson(
          response.errorBody()!!.charStream(),
          GeneralErrorDto::class.java
        )
        error?.let {
          return ApiResult(error = ApiFailure.ApiError(it.status_message))
        }
          ?: throw HttpException(response)

      }
    } catch (throwable: Throwable) {
      return ApiResult(error = throwable.map())
    }
  }
  }
