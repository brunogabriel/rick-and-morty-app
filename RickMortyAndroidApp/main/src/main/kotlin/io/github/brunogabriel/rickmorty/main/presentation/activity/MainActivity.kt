package io.github.brunogabriel.rickmorty.main.presentation.activity

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.rickmorty.main.R

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}