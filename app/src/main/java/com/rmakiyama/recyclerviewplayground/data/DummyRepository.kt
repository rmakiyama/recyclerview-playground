package com.rmakiyama.recyclerviewplayground.data

import androidx.lifecycle.LiveData
import com.rmakiyama.recyclerviewplayground.model.User

interface DummyRepository {

    val dummies: LiveData<List<User>>

    suspend fun toggleFavorite(user: User)
    suspend fun changeUser(user: User)
    suspend fun changePhoto(user: User)
}
