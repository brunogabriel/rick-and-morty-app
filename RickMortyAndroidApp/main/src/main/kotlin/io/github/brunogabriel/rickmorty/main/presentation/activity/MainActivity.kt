package io.github.brunogabriel.rickmorty.main.presentation.activity

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.rickmorty.main.databinding.ActivityMainBinding

class MainActivity : DaggerAppCompatActivity() {

    // TODO: Verify view binding and data binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupView() {

    }
}