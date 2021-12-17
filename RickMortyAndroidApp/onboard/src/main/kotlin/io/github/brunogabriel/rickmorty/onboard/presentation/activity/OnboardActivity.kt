package io.github.brunogabriel.rickmorty.onboard.presentation.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.rickmorty.deeplink.domain.DeeplinkHandler
import io.github.brunogabriel.rickmorty.onboard.R
import io.github.brunogabriel.rickmorty.onboard.databinding.ActivityOnboardBinding
import io.github.brunogabriel.rickmorty.onboard.presentation.viewmodel.OnboardViewModel
import io.github.brunogabriel.rickmorty.shared.extensions.fromHtml
import io.github.brunogabriel.rickmorty.shared.extensions.getLocationOnScreen
import io.github.brunogabriel.rickmorty.shared.viewmodel.NavigationEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: OnboardViewModel

    @Inject
    lateinit var deeplinkHandler: DeeplinkHandler

    private lateinit var binding: ActivityOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        with(binding) {
            lifecycleOwner = this@OnboardActivity
            viewModel = this@OnboardActivity.viewModel
        }
        setContentView(binding.root)
        binding.developersText.text = getString(R.string.onboard_develop_message).fromHtml()
        animateScreen()
        listenEvents()
    }

    private fun listenEvents() {
        lifecycleScope.launch {
            viewModel.deeplinkEvent.collect { event ->
                when (event) {
                    is NavigationEvent.Deeplink -> {
                        deeplinkHandler.process(event.deeplink)?.let {
                            startActivity(it)
                            finish()
                        }
                    }
                    is NavigationEvent.Url -> {
                        // TODO:
                    }
                }
            }
        }
    }

    private fun animateScreen() {
        window.decorView.doOnPreDraw {
            val contentActionsViewPosition = binding.contentActionsView.getLocationOnScreen()
            val translationPosition =
                (contentActionsViewPosition.y - binding.contentActionsView.height)

            val fadeInAnimation = ObjectAnimator.ofFloat(
                binding.contentActionsView,
                View.ALPHA,
                0f,
                1f
            ).apply {
                duration = 500
            }
            ObjectAnimator.ofFloat(
                binding.logoImageView,
                View.TRANSLATION_Y,
                -translationPosition.toFloat()
            ).apply {
                duration = 1_000
                doOnEnd { fadeInAnimation.start() }
                start()
            }
        }
    }
}