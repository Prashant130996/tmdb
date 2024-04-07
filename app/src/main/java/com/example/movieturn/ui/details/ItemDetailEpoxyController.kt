package com.example.movieturn.ui.details

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.example.movieturn.R
import com.example.movieturn.databinding.ModelEpisodeCarouselBinding
import com.example.movieturn.databinding.ModelMovieDetailsDataPointBinding
import com.example.movieturn.databinding.ModelMovieDetailsDesBinding
import com.example.movieturn.databinding.ModelMovieDetailsHeaderBinding
import com.example.movieturn.databinding.ModelMovieDetailsImageBinding
import com.example.movieturn.databinding.ModelTitleBinding
import com.example.movieturn.domain.models.Movie
import com.example.movieturn.domain.models.Season
import com.example.movieturn.domain.models.Series
import com.example.movieturn.epoxy.LoadingEpoxyModel
import com.example.movieturn.epoxy.ViewBindingKotlinModel
import com.example.movieturn.utils.MediaType
import com.example.movieturn.utils.loadImage

class ItemDetailEpoxyController(private val onFavClick: (Int, Boolean, MediaType) -> Unit) :
    EpoxyController() {

    private var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var movie: Movie? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    var series: Series? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("Loader").addTo(this)
            return
        }
        if (movie == null && series == null) {
            return
        }

        if (movie != null) {
            HeaderEpoxyModel(movie, null, onFavClick).id("header")
                .addTo(this)

            ImageEpoxyModel(movie!!.backdropPath).id("image").addTo(this)

            DataPointEpoxyModel("Release Date", movie!!.releaseDate).id("data_1").addTo(this)
            DataPointEpoxyModel("Genre", movie!!.genres.joinToString(separator = ", ")).id("data_2")
                .addTo(this)
            DescriptionEpoxyModel(movie!!.overview).id("header").addTo(this)
        }

        if (series != null) {
            HeaderEpoxyModel(null, series, onFavClick).id("header")
                .addTo(this)

            ImageEpoxyModel(series!!.backdropPath).id("image").addTo(this)

            DataPointEpoxyModel("Genre", series!!.genres.joinToString(separator = ", "))
                .id("data_2").addTo(this)
            DescriptionEpoxyModel(series!!.overview).id("header").addTo(this)

            if (series!!.seasons.isNotEmpty()) {
                val items = series!!.seasons.map {
                    EpisodeCarouselItemEpoxyModel(it).id(it.id)
                }
                TitleEpoxyModel(title = "Seasons").id("title_seasons").addTo(this)

                CarouselModel_()
                    .id("season_carousel")
                    .models(items)
                    .numViewsToShowOnScreen(1.15f)
                    .addTo(this)
            }
        }
    }

    data class HeaderEpoxyModel(
        val movie: Movie? = null,
        val series: Series? = null,
        val onFavClick: (Int, Boolean, MediaType) -> Unit
    ) :
        ViewBindingKotlinModel<ModelMovieDetailsHeaderBinding>(R.layout.model_movie_details_header) {
        override fun ModelMovieDetailsHeaderBinding.bind() {
            nameTextView.text = movie?.title ?: series?.name
            var isFav = movie?.isFav ?: series?.isFav
            val imgRes = if (isFav == true) R.drawable.ic_fav else R.drawable.ic_fav_outline
            val id = movie?.id ?: series?.id
            val type = movie?.mediaType ?: series?.mediaType
            favIv.setImageResource(imgRes)
            favIv.setOnClickListener {
                if (isFav == true) favIv.setImageResource(R.drawable.ic_fav_outline)
                else favIv.setImageResource(R.drawable.ic_fav)
                isFav = !isFav!!
                onFavClick(id!!, isFav!!, type!!)
            }
        }
    }

    data class DescriptionEpoxyModel(val name: String) :
        ViewBindingKotlinModel<ModelMovieDetailsDesBinding>(R.layout.model_movie_details_des) {
        override fun ModelMovieDetailsDesBinding.bind() {
            desTextView.text = name
        }
    }

    data class ImageEpoxyModel(val imageUrl: String) :
        ViewBindingKotlinModel<ModelMovieDetailsImageBinding>(R.layout.model_movie_details_image) {
        override fun ModelMovieDetailsImageBinding.bind() {
            Glide.with(headerImageView).load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String,
    ) : ViewBindingKotlinModel<ModelMovieDetailsDataPointBinding>(R.layout.model_movie_details_data_point) {
        override fun ModelMovieDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }

    data class EpisodeCarouselItemEpoxyModel(val season: Season) :
        ViewBindingKotlinModel<ModelEpisodeCarouselBinding>(R.layout.model_episode_carousel) {
        override fun ModelEpisodeCarouselBinding.bind() {
            seasonName.text = season.name
            seasonEpisodeCount.text = root.context.getString(R.string.episodes, season.episodeCount)
            seasonAirDate.text = season.airDate
        }
    }

    data class TitleEpoxyModel(val title: String) :
        ViewBindingKotlinModel<ModelTitleBinding>(R.layout.model_title) {
        override fun ModelTitleBinding.bind() {
            episodeTitleTv.text = title
        }
    }
}