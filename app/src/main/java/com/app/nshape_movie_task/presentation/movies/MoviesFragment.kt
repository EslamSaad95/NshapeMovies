package com.app.nshape_movie_task.presentation.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.nshape_movie_task.R
import com.app.nshape_movie_task.databinding.FragmentMoviesBinding
import com.app.nshape_movie_task.domain.common.ApiFailure
import com.app.nshape_movie_task.domain.entity.MoviesEntity
import com.app.nshape_movie_task.presentation.utils.extensions.linearLayoutManager
import com.app.nshape_movie_task.presentation.utils.extensions.showLongSnackBar
import com.app.nshape_movie_task.presentation.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
  private  val binding by lazy { FragmentMoviesBinding.inflate(layoutInflater) }
  private val viewModel by viewModels<MoviesViewModel>()
  private val moviesAdapter by lazy { MoviesRvAdapter() }




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
      viewModel = this@MoviesFragment.viewModel
    }
    observerMovies()
    observerErrorLiveData()
    observeSnackBarLiveData()
    observeFavItemLiveData()
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
  private fun observerMovies() {
    viewModel.moviesLiveData.observe(viewLifecycleOwner) {
      it?.let {
        moviesAdapter.fill(it)
        initTrendingMoviesRv()
      }
    }
  }

  private fun observeSnackBarLiveData() {
    viewModel.snackBarLiveData.observe(viewLifecycleOwner) {
      it?.let {
        binding.rvMovies.showLongSnackBar(it)
        viewModel.snackBarLiveData.value = null
      }
    }
  }

  private fun observeFavItemLiveData() {
    viewModel.favItemUpdateLiveData.observe(viewLifecycleOwner) {
      it?.let { favCheck ->
          moviesAdapter.getItem(position = favCheck.position).addToFavourite = favCheck.isFav
          moviesAdapter.notifyItemChanged(favCheck.position)
        }
      }
    }


  private fun initTrendingMoviesRv() {
    binding.rvMovies.apply {
      linearLayoutManager()
      adapter = moviesAdapter
      moviesAdapter.setOnClickListener { clickedView, item, position ->
        if (clickedView.id == R.id.ivFav) {
          if (item.addToFavourite.not())
            addToDataBase(item, position)
          else
            removeFromDatabase(item, position)
        }

      }
    }
  }

  private fun addToDataBase(item:MoviesEntity,position:Int)
  {
    item.addToFavourite=true
    viewModel.addToDatabase(item, position)
  }

  private fun removeFromDatabase(item:MoviesEntity,position:Int)
  {
    item.addToFavourite=false
    viewModel.removeFromDatabase(item, position)
  }



}