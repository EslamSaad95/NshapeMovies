package com.app.nshape_movie_task.presentation.movies

import androidx.core.content.ContextCompat
import com.app.nshape_movie_task.R
import com.app.nshape_movie_task.databinding.ItemMoviesBinding
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.presentation.utils.base.BaseAdapter
import com.app.nshape_movie_task.presentation.utils.extensions.load

class MoviesRvAdapter : BaseAdapter<ItemMoviesBinding, MoviesEntity>() {

  override fun setContent(binding: ItemMoviesBinding, item: MoviesEntity, position: Int) {
    binding.apply {
      ivMoviePoster.load(item.moviePoster.toString())
      tvMovieTitle.text = item.movieName
      tvMovieRating.text = item.rating.toString()
      tvMovieReleaseDate.text = item.releaseDate
      if (item.addToFavourite)
        ivFav.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.ic_fav_selected))
      else
        ivFav.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.ic_fav_default))

      root.setOnClickListener { onViewClicked(it, item, position) }
      ivFav.setOnClickListener { onViewClicked(it, item, position) }
    }
  }
}