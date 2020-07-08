package com.rmakiyama.recyclerviewplayground.model

import androidx.recyclerview.widget.DiffUtil
import java.util.UUID
import kotlin.random.Random

data class Dummy(
    val id: String = UUID.randomUUID().toString(),
    val number: Int = Random.nextInt(0, 100),
    val imageUrl: String = "https://randomuser.me/api/portraits/men/${Random.nextInt(0, 50)}.jpg",
    val isFavorite: Boolean = false
)

object DummyDiff : DiffUtil.ItemCallback<Dummy>() {

    override fun areItemsTheSame(oldItem: Dummy, newItem: Dummy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dummy, newItem: Dummy): Boolean {
        return oldItem == newItem
    }
}
