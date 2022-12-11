package com.app.nshape_movie_task.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.app.nshape_movie_task.R
import com.app.nshape_movie_task.databinding.FragmentMovieDetailsBinding
import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.presentation.utils.extensions.load
import com.app.nshape_movie_task.presentation.utils.extensions.showLongSnackBar
import com.app.nshape_movie_task.presentation.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

  private  val binding by lazy { FragmentMovieDetailsBinding.inflate(layoutInflater) }
  private val viewModel by viewModels<MovieDetailsViewModel>()
  private lateinit var movieDetailsEntity: MoviesEntity



  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    with(binding) {
      lifecycleOwner = viewLifecycleOwner
      viewModel = this@MovieDetailsFragment.viewModel
    }
    viewModel.getMovieDetails(arguments?.getInt("movieId")?:0)
    observerErrorLiveData()
    observeMovieDetailsLiveData()
    observeSnackBarLiveData()
    observeFavItemLiveData()
    setOnViewsListeners()
  }

  private fun observerErrorLiveData() {
    viewModel.errorLiveData.observe(viewLifecycleOwner) {

      if (it is ApiFailure.ConnectionError)
        binding.tvErrorMsg.setCompoundDrawablesWithIntrinsicBounds(
          0,
          R.drawable.ic_no_connection,
          0,
          0
        )
      else
        binding.tvErrorMsg.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
      binding.tvErrorMsg.apply {
        visible()
        it.error?.let { text = it }
        it.errorResId?.let { text = getText(it) }
      }
    }
  }

  private fun observeMovieDetailsLiveData() {
    viewModel.movieDetailsLiveData.observe(viewLifecycleOwner) {
      binding.apply {
        movieDetailsEntity=it
        ivMoviePoster.load(it.moviePoster.toString())
        tvMovieTitle.text=it.movieName
        tvMovieOverview.text=it.movieOverview
        tvMovieReleaseDate.text=it.releaseDate
        tvMovieRating.text=it.rating.toString()
        tvMovieVoteAverage.text=getString(R.string.movieDetails_fr_vote_count,it.movieVoteAverage.toString())
        tvMovieLang.text=getString(R.string.movieDetails_fr_language,it.movieLanguage)
        if(it.addToFavourite)
          ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_fav_selected))
        else
          ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_fav_default))
      }
    }
  }

  private fun setOnViewsListeners()
  {
    binding.ivFav.setOnClickListener {
      if(movieDetailsEntity.addToFavourite)
        removeFromDatabase(movieDetailsEntity)
      else
        addToDataBase(movieDetailsEntity)
    }
  }

  private fun observeSnackBarLiveData() {
    viewModel.snackBarLiveData.observe(viewLifecycleOwner) {
      it?.let {
        binding.ivMoviePoster.showLongSnackBar(it)
        viewModel.snackBarLiveData.value = null
      }
    }
  }

  private fun observeFavItemLiveData() {
    viewModel.favItemUpdateLiveData.observe(viewLifecycleOwner) {
      it?.let { favCheck ->
        if(favCheck.isFav)
          binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable
            .ic_fav_selected))
        else
         binding.ivFav.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable
           .ic_fav_default))
      }
    }
  }

  private fun addToDataBase(item: MoviesEntity)
  {
    item.addToFavourite=true
    viewModel.addToDatabase(item)
  }

  private fun removeFromDatabase(item: MoviesEntity)
  {
    item.addToFavourite=false
    viewModel.removeFromDatabase(item)
  }

}