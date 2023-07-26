package com.space.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.window.SplashScreen
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.space.movieapp.databinding.ActivityMoviesBinding
import com.space.movieapp.presentation.favorites.ui.FavoritesFragment
import com.space.movieapp.presentation.home.ui.HomeFragment
import com.space.movieapp.presentation.splash_screen.SplashFragment
import com.space.movieapp.presentation.views.CustomBottomNavigation
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