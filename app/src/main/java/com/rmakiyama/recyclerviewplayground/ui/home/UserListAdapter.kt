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
import com.rmakiyama.recyclerviewplayground.model.User
import com.rmakiyama.recyclerviewplayground.model.DummyDiff

class UserListAdapter(
    private val onClickFavoriteListener: (user: User) -> Unit
) : ListAdapter<User, UserViewHolder>(DummyDiff) {

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
    private val onClickFavoriteListener: (user: User) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        val binding = ItemDummyBinding.bind(itemView)
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
