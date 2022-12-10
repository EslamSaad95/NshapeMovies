package com.app.nshape_movie_task.domain.common

data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null
)