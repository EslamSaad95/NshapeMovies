package com.app.nshape_movie_task.presentation.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.domain.useCase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: MoviesUseCase) : ViewModel() {

  private val _moviesLiveData by lazy { MutableLiveData<List<MoviesEntity>>() }
  val moviesLiveData get() = _moviesLiveData

  private val _loadingLiveData by lazy { MutableLiveData<Boolean>() }
  val loadingLiveData get() = _loadingLiveData

  private val _errorLiveData by lazy { MutableLiveData<ApiFailure>() }
  val errorLiveData get() = _errorLiveData

  init {
    getMovies()
  }

  private fun getMovies() {
    _loadingLiveData.value = true
    viewModelScope.launch {
      useCase.getTrendingMovies().collect { response ->
        response.value?.let {
          _loadingLiveData.value = false
          _moviesLiveData.value = it
        }

        response.error?.let {
          _loadingLiveData.value = false
          _errorLiveData.value = it
        }
      }
    }
  }
}