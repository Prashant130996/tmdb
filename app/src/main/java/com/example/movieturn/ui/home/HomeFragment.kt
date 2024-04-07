package com.example.movieturn.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.movieturn.R
import com.example.movieturn.databinding.FragmentHomeBinding
import com.example.movieturn.ui.viewBinding
import com.example.movieturn.utils.MediaType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val allDataViewModel by viewModels<AllDataViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trendingPagingEpoxyController = TrendingPagingEpoxyController(::onItemSelected)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                allDataViewModel.flow.collectLatest {
                    trendingPagingEpoxyController.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            trendingPagingEpoxyController.loadStateFlow.collect {
                binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                binding.appendProgress.isVisible = it.source.append is LoadState.Loading
            }
        }
        binding.allMoviesRc.setController(trendingPagingEpoxyController)
    }

    private fun onItemSelected(itemId: Int, mediaType: MediaType) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                itemId = itemId,
                mediaType = mediaType
            )
        findNavController().navigate(action)
    }


}