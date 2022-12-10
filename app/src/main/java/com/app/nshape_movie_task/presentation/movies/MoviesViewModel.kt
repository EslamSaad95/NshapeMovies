package com.app.nshape_movie_task.presentation.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.domain.useCase.MoviesDataBaseUseCase
import com.app.nshape_movie_task.domain.useCase.MoviesUseCase
import com.app.nshape_movie_task.presentation.utils.localEntity.FavCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: MoviesUseCase,
                                          private val dbUseCase: MoviesDataBaseUseCase) : ViewModel() {

  private val _moviesLiveData by lazy { MutableLiveData<List<MoviesEntity>>() }
  val moviesLiveData get() = _moviesLiveData

  private val _loadingLiveData by lazy { MutableLiveData<Boolean>() }
  val loadingLiveData get() = _loadingLiveData

  private val _errorLiveData by lazy { MutableLiveData<ApiFailure>() }
  val errorLiveData get() = _errorLiveData

  private val _snackBarLiveData by lazy { MutableLiveData<String>() }
  val snackBarLiveData get() = _snackBarLiveData

  private val _favItemUpdateLiveData by lazy { MutableLiveData<FavCheck>() }
  val favItemUpdateLiveData get() = _favItemUpdateLiveData

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

  fun addToDatabase(entity: MoviesEntity, position: Int) {
    viewModelScope.launch {
      try {
        dbUseCase.addTeam(entity)
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, true, position)
      } catch (e: Exception) {
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, false, position)
        _snackBarLiveData.value = e.message.toString()
      }
    }
  }

  fun removeFromDatabase(entity: MoviesEntity, position: Int) {
    viewModelScope.launch {
      try {
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, false, position)
        dbUseCase.removeFromTeams(entity)
      } catch (e: Exception) {
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, true, position)
        _snackBarLiveData.value = e.message.toString()
      }
    }
  }
}