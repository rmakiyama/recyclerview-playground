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

object DummyDiff : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
