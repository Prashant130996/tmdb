package com.example.movieturn.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieturn.R
import com.example.movieturn.databinding.FragmentFavBinding
import com.example.movieturn.databinding.FragmentHomeBinding
import com.example.movieturn.domain.mappers.MovieMapper
import com.example.movieturn.domain.mappers.TrendingMapper
import com.example.movieturn.domain.models.Movie
import com.example.movieturn.domain.models.Series
import com.example.movieturn.domain.models.Trending
import com.example.movieturn.ui.viewBinding
import com.example.movieturn.utils.MediaType
import com.example.movieturn.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment(R.layout.fragment_fav) {
    private val binding by viewBinding(FragmentFavBinding::bind)
    private val favViewModel by viewModels<FavViewModel>()
    private lateinit var uiFavEpoxyController: UiFavEpoxyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiFavEpoxyController = UiFavEpoxyController(::onItemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allFavRc.setController(uiFavEpoxyController)
    }

    private fun onItemClick(itemId: Int, mediaType: MediaType) {
        val action =
            FavFragmentDirections.actionDownloadFragmentToDetailsFragment(mediaType, itemId)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        favViewModel.getFavMovie()
        favViewModel.getFavSeries()
        favViewModel.getFavMovieLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                favViewModel.isMovieFetched = true
                favViewModel.addData()
            } else toast("empty")
        }
        favViewModel.getFavTvLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                favViewModel.isTvFetched = true
                favViewModel.addData()
            }else toast("empty")
        }
        favViewModel.getList.observe(viewLifecycleOwner) {
            it?.run {
                uiFavEpoxyController.setData(it)
            } ?: toast("Unable to fetch data")
        }
    }

    override fun onPause() {
        super.onPause()
        favViewModel.isMovieFetched = false
        favViewModel.isTvFetched = false
    }
}