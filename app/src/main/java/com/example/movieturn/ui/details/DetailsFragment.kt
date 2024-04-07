package com.example.movieturn.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieturn.R
import com.example.movieturn.databinding.FragmentDetailsBinding
import com.example.movieturn.model.favState.AddDelFavBody
import com.example.movieturn.ui.viewBinding
import com.example.movieturn.utils.MediaType
import com.example.movieturn.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val detailsViewModel by viewModels<DetailsViewModel>()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        val epoxyController = ItemDetailEpoxyController(::onFavClick)
        when (args.mediaType) {
            MediaType.MOVIE -> {
                detailsViewModel.getMovieDetails(args.itemId)
                detailsViewModel.getMovieDetailsLiveData.observe(viewLifecycleOwner) { movie ->
                    epoxyController.movie = movie
                    if (movie == null) {
                        toast("Unsuccessful network call")
                        findNavController().popBackStack()
                        return@observe
                    }
                }
            }

            MediaType.SERIES -> {
                detailsViewModel.getSeriesDetails(args.itemId)
                detailsViewModel.getSeriesDetailsLiveData.observe(viewLifecycleOwner) { series ->
                    epoxyController.series = series
                    if (series == null) {
                        toast("Unsuccessful network call")
                        findNavController().popBackStack()
                        return@observe
                    }
                }
            }

            else -> toast("Check item id")
        }
        binding.epoxyRv.setControllerAndBuildModels(epoxyController)
    }

    private fun setListener() = binding.run {
        goBackIv.setOnClickListener { findNavController().popBackStack() }
    }

    private fun onFavClick(id: Int, isFav: Boolean, type: MediaType) {
        detailsViewModel.addRemoveFavMovie(
            AddDelFavBody(
                media_id = id,
                favorite = isFav,
                media_type = type.point
            )
        )
    }
}