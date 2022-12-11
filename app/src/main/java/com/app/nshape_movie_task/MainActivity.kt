package com.app.nshape_movie_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.nshape_movie_task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
  private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController }
  private val appBarConfiguration by lazy {
    AppBarConfiguration(
      setOf(
        R.id.moviesFragment
      )
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    initNavigation()
  }

  private fun initNavigation() {
    setSupportActionBar(binding.toolbar)
    setupActionBarWithNavController(navController, appBarConfiguration)
    binding.toolbar.setNavigationOnClickListener {
      onBackPressedDispatcher.onBackPressed()
    }
  }
}