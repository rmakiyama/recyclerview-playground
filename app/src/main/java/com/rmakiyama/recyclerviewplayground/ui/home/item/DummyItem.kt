package com.rmakiyama.recyclerviewplayground.ui.home.item

import android.widget.ImageButton
import coil.api.load
import coil.transform.CircleCropTransformation
import com.rmakiyama.recyclerviewplayground.R
import com.rmakiyama.recyclerviewplayground.databinding.ItemDummyBinding
import com.rmakiyama.recyclerviewplayground.model.User
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

data class DummyItem(
    val user: User,
    val onClickFavoriteListener: (user: User) -> Unit
) : Item<GroupieViewHolder>(user.id.hashCode().toLong()) {

    override fun getLayout() = R.layout.item_dummy

    override fun bind(
        viewHolder: GroupieViewHolder,
        position: Int
    ) {
        val binding = ItemDummyBinding.bind(viewHolder.itemView)

        binding.image.load(user.imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.uuid.text = user.id
        setImageRes(binding.favorite, user.isFavorite)
        binding.favorite.setOnClickListener { onClickFavoriteListener(user) }
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
