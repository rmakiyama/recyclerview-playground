package com.rmakiyama.recyclerviewplayground.data

import com.rmakiyama.recyclerviewplayground.model.Dummy
import kotlinx.coroutines.flow.Flow

interface DummyRepository {

    fun dummies(): Flow<List<Dummy>>

    suspend fun save(dummy: Dummy)
}
