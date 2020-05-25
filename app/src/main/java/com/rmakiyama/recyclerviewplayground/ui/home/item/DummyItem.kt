package com.rmakiyama.recyclerviewplayground.ui.home.item

import android.widget.ImageButton
import coil.api.load
import coil.transform.CircleCropTransformation
import com.rmakiyama.recyclerviewplayground.R
import com.rmakiyama.recyclerviewplayground.databinding.ItemDummyBinding
import com.rmakiyama.recyclerviewplayground.model.Dummy
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

data class DummyItem(
    val dummy: Dummy,
    val onClickFavoriteListener: (dummy: Dummy) -> Unit
) : Item<GroupieViewHolder>(dummy.id.hashCode().toLong()) {

    override fun getLayout() = R.layout.item_dummy

    override fun bind(
        viewHolder: GroupieViewHolder,
        position: Int
    ) {
        val binding = ItemDummyBinding.bind(viewHolder.itemView)

        binding.image.load(dummy.imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.uuid.text = dummy.id
        setImageRes(binding.favorite, dummy.isFavorite)
        binding.favorite.setOnClickListener { onClickFavoriteListener(dummy) }
    }

    private fun setImageRes(
        imageButton: ImageButton,
        isFavorite: Boolean
    ) {
        val imageRes = if (isFavorite) {
            R.drawable.ic_favorite
        } else {
            R.drawable.ic_favorite_border
        }
        imageButton.setImageResource(imageRes)
    }
}
