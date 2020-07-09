package com.rmakiyama.recyclerviewplayground.model

import androidx.recyclerview.widget.DiffUtil
import java.util.UUID
import kotlin.random.Random

data class User(
    val id: String = UUID.randomUUID().toString(),
    val number: Int = Random.nextInt(0, 100),
    val imageUrl: String = "https://randomuser.me/api/portraits/men/${Random.nextInt(0, 50)}.jpg",
    val isFavorite: Boolean = false
)

object UserDiff : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: User, newItem: User): Any? {
        return when {
            oldItem.imageUrl != newItem.imageUrl -> {
                Payload.ImageUrl(newItem.imageUrl)
            }
            oldItem.isFavorite != newItem.isFavorite -> {
                Payload.IsFavorite(newItem.isFavorite)
            }
            else -> null
        }
    }

    sealed class Payload {
        data class ImageUrl(val value: String) : Payload()
        data class IsFavorite(val value: Boolean) : Payload()
    }
}
