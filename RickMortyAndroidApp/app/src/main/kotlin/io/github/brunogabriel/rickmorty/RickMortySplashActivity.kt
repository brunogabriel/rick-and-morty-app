package io.github.brunogabriel.rickmorty

import android.content.Intent
import android.os.Bundle
import dagger.android.DaggerActivity
import io.github.brunogabriel.rickmorty.onboard.presentation.activity.OnboardActivity

class RickMortySplashActivity : DaggerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, OnboardActivity::class.java))
        finish()
    }
}