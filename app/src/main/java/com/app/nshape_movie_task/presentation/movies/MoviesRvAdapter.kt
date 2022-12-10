package com.app.nshape_movie_task.presentation.movies

import com.app.nshape_movie_task.databinding.ItemMoviesBinding
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.presentation.utils.base.BaseAdapter
import com.app.nshape_movie_task.presentation.utils.extensions.load

class MoviesRvAdapter:BaseAdapter<ItemMoviesBinding,MoviesEntity>() {

  override fun setContent(binding: ItemMoviesBinding, item: MoviesEntity, position: Int) {
    binding.apply {
      ivMoviePoster.load(item.moviePoster)
      tvMovieTitle.text=item.movieName
      tvMovieRating.text=item.rating.toString()
      tvMovieReleaseDate.text=item.releaseDate

    }
  }
}