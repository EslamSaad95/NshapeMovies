package com.app.nshape_movie_task.data.network

import com.app.nshape_movie_task.data.network.Dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

  @GET("discover/movie?primary_release_year=2010&sort_by=vote_average.desc")
  suspend fun getMovies():Response<MoviesDto>
}