package com.rmakiyama.recyclerviewplayground.model

import java.util.UUID
import kotlin.random.Random

data class Dummy(
    val id: String = UUID.randomUUID().toString(),
    val number: Int = Random.nextInt(0, 100)
)
