package io.github.brunogabriel.rickmorty.main.episodes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import io.github.brunogabriel.rickmorty.main.databinding.FragmentEpisodesBinding
import io.github.brunogabriel.rickmorty.main.episodes.presentation.adapter.EpisodeRecyclerViewAdapter
import io.github.brunogabriel.rickmorty.main.episodes.presentation.viewmodel.EpisodeViewModel
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import io.github.brunogabriel.rickmorty.shared.exception.PaginationException
import io.github.brunogabriel.rickmorty.shared.extensions.hide
import io.github.brunogabriel.rickmorty.shared.extensions.show
import io.github.brunogabriel.rickmorty.shared.recyclerview.adapter.GenericItem
import io.github.brunogabriel.rickmorty.shared.recyclerview.pagination.RecyclerPaginationListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: EpisodeViewModel

    private var isFirstLoading = true

    private var page = 1
    private var loadedAllItems = false

    private val recyclerViewAdapter by lazy {
        EpisodeRecyclerViewAdapter {
            viewModel.loadData(page)
        }
    }

    private lateinit var binding: FragmentEpisodesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenEvents()
        setupView()
        viewModel.loadData(page)
    }


    private fun setupView() {
        setupRecycleView()
        binding.tryAgainView.bind {
            viewModel.loadData(page)
        }
    }

    private fun setupRecycleView() {
        with(binding.recyclerView) {
            adapter = recyclerViewAdapter
            addOnScrollListener(RecyclerPaginationListener(layoutManager as LinearLayoutManager) {
                if (isFirstLoading.not() && recyclerViewAdapter.isLastItemLoadingOrTryAgain()
                        .not() && loadedAllItems.not()
                ) {
                    viewModel.loadData(page)
                }
            })
        }
    }

    private fun listenEvents() {
        lifecycleScope.launch {
            viewModel.episodeResult.collect { result ->
                when (result) {
                    is NetworkResult.Loading -> treatLoadingEvent()
                    is NetworkResult.Error -> treatErrorEvent(result)
                    is NetworkResult.Success -> treatSuccessEvent(result)
                    is NetworkResult.None -> {
                        // Do nothing
                    }
                }
            }
        }
    }

    private fun treatSuccessEvent(result: NetworkResult.Success<List<Any>>) {
        isFirstLoading = false
        binding.tryAgainView.hide()
        binding.shimmerLayout.hide()
        recyclerViewAdapter.addModels(GenericItem.View(result.data))
        page++
    }

    private fun treatErrorEvent(result: NetworkResult.Error) {
        binding.shimmerLayout.hide()
        if (result.throwable is PaginationException) {
            loadedAllItems = true
            recyclerViewAdapter.removeLoadingOrTryAgain()
        } else {
            if (isFirstLoading) {
                binding.tryAgainView.show()
            } else {
                recyclerViewAdapter.addTryAgain()
            }
        }
    }

    private fun treatLoadingEvent() {
        binding.tryAgainView.hide()
        if (isFirstLoading) {
            binding.shimmerLayout.show()
        } else {
            recyclerViewAdapter.addLoading()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerLayout.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerLayout.stopShimmer()
    }
}

