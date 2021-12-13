package io.github.brunogabriel.rickmorty.main.characters.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.android.support.DaggerFragment
import io.github.brunogabriel.rickmorty.main.characters.presentation.adapter.CharacterAdapterViewType
import io.github.brunogabriel.rickmorty.main.characters.presentation.adapter.CharacterRecyclerViewAdapter
import io.github.brunogabriel.rickmorty.main.characters.presentation.viewmodel.CharacterViewModel
import io.github.brunogabriel.rickmorty.main.databinding.FragmentCharactersBinding
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: CharacterViewModel

    private val recyclerViewAdapter by lazy {
        CharacterRecyclerViewAdapter()
    }

    private lateinit var binding: FragmentCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        listenEvents()
        viewModel.loadData()
    }

    private fun setupView() {
        with(binding.recyclerView) {
            adapter = recyclerViewAdapter
            // TODO: Decoration
        }
    }

    private fun listenEvents() {
        lifecycleScope.launch {
            viewModel.characterResult.collect { result ->
                when (result) {
                    is NetworkResult.Success -> recyclerViewAdapter.addModels(
                        CharacterAdapterViewType.Characters(result.data)
                    )
                    // TODO: another branches
                }
            }
        }
    }
}