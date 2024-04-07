package com.example.movieturn.ui.favorite

import android.view.View
import android.view.ViewParent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.Glide
import com.example.movieturn.R
import com.example.movieturn.databinding.ItemFavBinding
import com.example.movieturn.databinding.LayoutNoConnectionBinding
import com.example.movieturn.domain.models.Trending
import com.example.movieturn.epoxy.ViewBindingKotlinModel
import com.example.movieturn.utils.MediaType

class UiFavEpoxyController(private val onItemCLick: (Int, MediaType) -> Unit) :
    TypedEpoxyController<List<Trending>>() {

    private var isLoading = true
        set(value) {
            field = value
            if (field) requestModelBuild()

        }

    override fun buildModels(data: List<Trending>?) {
        if (data.isNullOrEmpty()) {
            EmptyStateModel(
                emptyStateImageResId = R.drawable.no_connection,
                titleResId = R.string.no_connection,
                messageResId = R.string.no_connection_msg
            ).id("empty_state").addTo(this)

            return
        }
        isLoading = false
        data.forEach {
            UiFavModel(it, onItemCLick).id("item_id_${it.id}").addTo(this)
        }

    }

    data class EmptyStateModel(
        @DrawableRes val emptyStateImageResId: Int,
        @StringRes val titleResId: Int,
        @StringRes val messageResId: Int,
    ) : EpoxyModelWithHolder<EmptyStateHolder>() {

        override fun bind(holder: EmptyStateHolder) {
            // Bind data or set up views for the empty state
            holder.binding.searchIconBg.setImageResource(emptyStateImageResId)
            holder.binding.textv1.setText(titleResId)
            holder.binding.textv2.setText(messageResId)
        }

        override fun getDefaultLayout() = R.layout.layout_no_connection
        override fun createNewHolder(parent: ViewParent): EmptyStateHolder {
            return EmptyStateHolder()
        }

    }

    class EmptyStateHolder : EpoxyHolder() {
        lateinit var binding: LayoutNoConnectionBinding

        override fun bindView(itemView: View) {
            binding = LayoutNoConnectionBinding.bind(itemView)
        }
    }

    data class UiFavModel(val trending: Trending, val onItemCLick: (Int, MediaType) -> Unit) :
        ViewBindingKotlinModel<ItemFavBinding>(R.layout.item_fav) {
        override fun ItemFavBinding.bind() {
            nameTv.text = trending.name
            val rating = "Rating : ${trending.rating}"
            typeTv.text = rating
            Glide.with(imageView).load(trending.posterPath).into(imageView)
            root.setOnClickListener { onItemCLick(trending.id, trending.mediaType) }
        }
    }

}