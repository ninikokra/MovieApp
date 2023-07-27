package com.space.movieapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.space.movieapp.R
import com.space.movieapp.databinding.ActivityMoviesBinding
import com.space.movieapp.utils.viewBinding

class MoviesActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMoviesBinding::inflate)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navigationToUI()
        setBottomNavClicks()
        supportActionBar?.hide()

    }

    private fun setBottomNavClicks() {
        binding.customBottomNav.apply {
            setOnHomeButtonClicked {
                navController.navigate(R.id.homeFragment)
            }

            setOnFavoritesButtonClicked {
                navController.navigate(R.id.favoritesFragment)
            }
        }
    }

    private fun navigationToUI() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleBottomNavigationVisibility(destination.id)
        }
    }

    private fun handleBottomNavigationVisibility(destinationId: Int) {
        val showBottomNav = when (destinationId) {
            R.id.homeFragment, R.id.favoritesFragment -> true
            else -> false
        }
        binding.customBottomNav.visibility = if (showBottomNav) View.VISIBLE else View.GONE
    }
}