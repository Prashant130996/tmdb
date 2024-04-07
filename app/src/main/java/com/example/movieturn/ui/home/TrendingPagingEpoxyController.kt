package com.example.movieturn.ui.home

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.bumptech.glide.Glide
import com.example.movieturn.R
import com.example.movieturn.databinding.ModelMovieListItemBinding
import com.example.movieturn.domain.models.Trending
import com.example.movieturn.epoxy.ViewBindingKotlinModel
import com.example.movieturn.utils.MediaType

class TrendingPagingEpoxyController(private val onItemClick: (Int, MediaType) -> Unit) :
    PagingDataEpoxyController<Trending>() {

    override fun buildItemModel(currentPosition: Int, item: Trending?): EpoxyModel<*> {
        return TrendingListEpoxyModel(
            item = item!!,
            onItemClick = onItemClick
        ).id("Trending_${item.id}")
    }

    data class TrendingListEpoxyModel(
        val item: Trending,
        val onItemClick: (Int, MediaType) -> Unit
    ) :
        ViewBindingKotlinModel<ModelMovieListItemBinding>(R.layout.model_movie_list_item) {
        override fun ModelMovieListItemBinding.bind() {
            Glide.with(trendingImage).load(item.posterPath).into(trendingImage)
            root.setOnClickListener { onItemClick(item.id, item.mediaType) }
        }
    }
}