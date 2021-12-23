package io.github.brunogabriel.rickmorty.main.presentation.activity

import android.os.Bundle
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.rickmorty.main.R
import io.github.brunogabriel.rickmorty.main.databinding.ActivityMainBinding

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val fragHost by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHost
    }

    private val navController by lazy {
        fragHost.navController
    }

    private val navOnDestinationChangedListener by lazy {
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val title = when (destination.id) {
                R.id.characterFragment -> getString(R.string.nav_character)
                else -> ""
            }
            supportActionBar?.title = title
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(navOnDestinationChangedListener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(navOnDestinationChangedListener)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

}