package io.github.brunogabriel.rickmorty

import android.content.Intent
import android.os.Bundle
import dagger.android.DaggerActivity
import io.github.brunogabriel.rickmorty.main.presentation.activity.MainActivity

class FakeSplashActivity : DaggerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}