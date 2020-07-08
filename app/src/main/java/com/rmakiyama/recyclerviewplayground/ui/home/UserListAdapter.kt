package com.rmakiyama.recyclerviewplayground.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.rmakiyama.recyclerviewplayground.R
import com.rmakiyama.recyclerviewplayground.databinding.ItemDummyBinding
import com.rmakiyama.recyclerviewplayground.model.Dummy
import com.rmakiyama.recyclerviewplayground.model.DummyDiff

class UserListAdapter(
    private val onClickFavoriteListener: (dummy: Dummy) -> Unit
) : ListAdapter<Dummy, UserViewHolder>(DummyDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemDummyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding, onClickFavoriteListener)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}

class UserViewHolder(
    binding: ItemDummyBinding,
    private val onClickFavoriteListener: (dummy: Dummy) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dummy: Dummy) {
        val binding = ItemDummyBinding.bind(itemView)
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
