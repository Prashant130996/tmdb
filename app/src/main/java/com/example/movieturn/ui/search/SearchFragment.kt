package com.example.movieturn.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.movieturn.R
import com.example.movieturn.databinding.FragmentSearchBinding
import com.example.movieturn.ui.home.AllDataViewModel
import com.example.movieturn.ui.home.HomeFragmentDirections
import com.example.movieturn.ui.home.TrendingPagingEpoxyController
import com.example.movieturn.ui.viewBinding
import com.example.movieturn.utils.MediaType
import com.example.movieturn.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val allDataViewModel by viewModels<AllDataViewModel>()
    private lateinit var trendingPagingEpoxyController: TrendingPagingEpoxyController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trendingPagingEpoxyController = TrendingPagingEpoxyController(::onItemSelected)

        var job: Job? = null
        binding.searchTil.editText?.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(2000)
                editable?.let {
                    if (it.toString().isNotEmpty()) search(it.toString())
                }
            }
        }
    }

    private fun search(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                allDataViewModel.searchContent(query).collectLatest {
                    trendingPagingEpoxyController.submitData(it)
                }
            }
        }
        binding.searchRv.setController(trendingPagingEpoxyController)
    }

    private fun onItemSelected(itemId: Int, mediaType: MediaType) {
        if (mediaType == MediaType.PEOPLE) toast("Unable to show person details at the moment")
        else {
            val action =
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                    itemId = itemId,
                    mediaType = mediaType
                )
            findNavController().navigate(action)
        }
    }

}