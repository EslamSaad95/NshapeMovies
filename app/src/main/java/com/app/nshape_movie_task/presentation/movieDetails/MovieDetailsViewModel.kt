package com.app.nshape_movie_task.presentation.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.domain.useCase.MovieDetailsUseCase
import com.app.nshape_movie_task.domain.useCase.MoviesDataBaseUseCase
import com.app.nshape_movie_task.presentation.utils.localEntity.FavCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel  @Inject constructor(private val useCase: MovieDetailsUseCase,
                                                 private val dbUseCase: MoviesDataBaseUseCase
) : ViewModel() {

  private val _movieDetailsLiveData by lazy { MutableLiveData<MoviesEntity>() }
  val movieDetailsLiveData get() = _movieDetailsLiveData

  private val _loadingLiveData by lazy { MutableLiveData<Boolean>() }
  val loadingLiveData get() = _loadingLiveData

  private val _errorLiveData by lazy { MutableLiveData<ApiFailure>() }
  val errorLiveData get() = _errorLiveData

  private val _snackBarLiveData by lazy { MutableLiveData<String>() }
  val snackBarLiveData get() = _snackBarLiveData

  private val _favItemUpdateLiveData by lazy { MutableLiveData<FavCheck>() }
  val favItemUpdateLiveData get() = _favItemUpdateLiveData



   fun getMovieDetails(id:Int) {
    _loadingLiveData.value = true
    viewModelScope.launch {
      useCase.getMovieDetails(id).collect { response ->
        response.value?.let {
          _loadingLiveData.value = false
          _movieDetailsLiveData.value = it
        }

        response.error?.let {
          _loadingLiveData.value = false
          _errorLiveData.value = it
        }
      }
    }
  }

  fun addToDatabase(entity: MoviesEntity) {
    viewModelScope.launch {
      try {
        dbUseCase.addTeam(entity)
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, true, 0)
      } catch (e: Exception) {
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, false, 0)
        _snackBarLiveData.value = e.message.toString()
      }
    }
  }

  fun removeFromDatabase(entity: MoviesEntity) {
    viewModelScope.launch {
      try {
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, false, 0)
        dbUseCase.removeFromTeams(entity)
      } catch (e: Exception) {
        _favItemUpdateLiveData.value = FavCheck(entity.movieName, true, 0)
        _snackBarLiveData.value = e.message.toString()
      }
    }
  }
}