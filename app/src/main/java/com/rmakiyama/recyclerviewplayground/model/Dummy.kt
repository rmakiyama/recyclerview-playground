package com.rmakiyama.recyclerviewplayground.model

import java.util.UUID
import kotlin.random.Random

data class Dummy(
    val id: String = UUID.randomUUID().toString(),
    val number: Int = Random.nextInt(0, 100),
    val imageUrl: String = "https://randomuser.me/api/portraits/men/${Random.nextInt(0, 50)}.jpg",
    val isFavorite: Boolean = false
)
