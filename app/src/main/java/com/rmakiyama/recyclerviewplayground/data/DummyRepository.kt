package com.rmakiyama.recyclerviewplayground.data

import androidx.lifecycle.LiveData
import com.rmakiyama.recyclerviewplayground.model.Dummy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface DummyRepository {

    val dummies: LiveData<List<Dummy>>

    suspend fun toggleFavorite(dummy: Dummy)
}
