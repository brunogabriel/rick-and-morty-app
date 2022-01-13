package io.github.brunogabriel.rickmorty.deeplink.presentation

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.rickmorty.deeplink.domain.DeeplinkHandler
import javax.inject.Inject

class DeeplinkActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var deeplinkHandler: DeeplinkHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let { handleIntention(it) }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntention(it) }
    }

    private fun handleIntention(intent: Intent) {
        intent.data?.toString()?.let {
            deeplinkHandler.process(it)
        }
    }
}