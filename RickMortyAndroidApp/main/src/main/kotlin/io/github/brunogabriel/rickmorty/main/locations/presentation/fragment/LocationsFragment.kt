package io.github.brunogabriel.rickmorty.main.locations.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import io.github.brunogabriel.rickmorty.main.databinding.FragmentLocationsBinding
import io.github.brunogabriel.rickmorty.main.locations.presentation.adapter.LocationRecyclerViewAdapter
import io.github.brunogabriel.rickmorty.main.locations.presentation.viewmodel.LocationsViewModel
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import io.github.brunogabriel.rickmorty.shared.exception.PaginationException
import io.github.brunogabriel.rickmorty.shared.extensions.hide
import io.github.brunogabriel.rickmorty.shared.extensions.px
import io.github.brunogabriel.rickmorty.shared.extensions.show
import io.github.brunogabriel.rickmorty.shared.recyclerview.adapter.GenericItem
import io.github.brunogabriel.rickmorty.shared.recyclerview.decoration.RecyclerViewGridDecoration
import io.github.brunogabriel.rickmorty.shared.recyclerview.pagination.RecyclerPaginationListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: LocationsViewModel

    private var isFirstLoading = true

    private var page = 1
    private var loadedAllItems = false

    private val recyclerViewAdapter by lazy {
        LocationRecyclerViewAdapter {
            viewModel.loadData(page)
        }
    }

    private val spanSizeLookup by lazy {
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = if (
                recyclerViewAdapter.isLoadingOrTryAgainAtPosition(position)
            ) {
                2
            } else {
                1
            }
        }
    }

    private lateinit var binding: FragmentLocationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(
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
            val gridLayoutManager = (layoutManager as GridLayoutManager)
            adapter = recyclerViewAdapter
            addItemDecoration(RecyclerViewGridDecoration(16.px))
            gridLayoutManager.spanSizeLookup = spanSizeLookup
            addOnScrollListener(RecyclerPaginationListener(gridLayoutManager) {
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
            viewModel.locationResult.collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        binding.tryAgainView.hide()
                        if (isFirstLoading) {
                            binding.shimmerLayout.show()
                        } else {
                            recyclerViewAdapter.addLoading()
                        }
                    }

                    is NetworkResult.Error -> {
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

                    is NetworkResult.Success -> {
                        isFirstLoading = false
                        binding.tryAgainView.hide()
                        binding.shimmerLayout.hide()
                        recyclerViewAdapter.addModels(GenericItem.View(result.data))
                        page++
                    }

                    is NetworkResult.None -> {
                        // Do nothing
                    }
                }
            }
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