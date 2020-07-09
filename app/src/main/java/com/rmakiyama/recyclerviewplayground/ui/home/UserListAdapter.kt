package com.rmakiyama.recyclerviewplayground.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.rmakiyama.recyclerviewplayground.R
import com.rmakiyama.recyclerviewplayground.databinding.ItemDummyBinding
import com.rmakiyama.recyclerviewplayground.model.User
import com.rmakiyama.recyclerviewplayground.model.UserDiff
import timber.log.Timber

class UserListAdapter(
    private val onClickFavoriteListener: (user: User) -> Unit,
    private val onClickUserListener: (user: User) -> Unit,
    private val onClickImageListener: (user: User) -> Unit
) : ListAdapter<User, UserViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        Timber.i("info: onCreateViewHolder.")
        val binding = ItemDummyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(
            binding,
            onClickFavoriteListener,
            onClickUserListener,
            onClickImageListener
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Timber.i("info: onBindViewHolder.")
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.distinct().forEach { payload ->
                when (payload) {
                    is UserDiff.Payload.ImageUrl -> {
                        holder.updateImageUrl(payload.value)
                    }
                    is UserDiff.Payload.IsFavorite -> {
                        holder.updateFavorite(payload.value)
                    }
                }
            }
        }
    }
}

class UserViewHolder(
    binding: ItemDummyBinding,
    private val onClickFavoriteListener: (user: User) -> Unit,
    private val onClickUserListener: (user: User) -> Unit,
    private val onClickImageListener: (user: User) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        // ViewにUser情報を表示
        val binding = ItemDummyBinding.bind(itemView)
        binding.uuid.text = user.id
        loadImage(user.imageUrl)
        setImageRes(user.isFavorite)

        binding.root.setOnClickListener { onClickUserListener(user) }
        binding.favorite.setOnClickListener { onClickFavoriteListener(user) }
        binding.image.setOnClickListener { onClickImageListener(user) }
    }

    fun updateImageUrl(imageUrl: String) = loadImage(imageUrl)

    fun updateFavorite(isFavorite: Boolean) = setImageRes(isFavorite)

    private fun loadImage(imageUrl: String) {
        val binding = ItemDummyBinding.bind(itemView)
        binding.image.load(imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    private fun setImageRes(
        isFavorite: Boolean
    ) {
        val binding = ItemDummyBinding.bind(itemView)
        val imageRes = if (isFavorite) {
            R.drawable.ic_favorite
        } else {
            R.drawable.ic_favorite_border
        }
        binding.favorite.setImageResource(imageRes)
    }
}
