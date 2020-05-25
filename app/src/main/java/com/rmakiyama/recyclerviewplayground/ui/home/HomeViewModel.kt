package com.rmakiyama.recyclerviewplayground.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rmakiyama.recyclerviewplayground.data.DummyRepository
import com.rmakiyama.recyclerviewplayground.model.Dummy
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: DummyRepository
) : ViewModel() {

    val dummies = repository.dummies

    fun toggleFavorite(dummy: Dummy) {
        viewModelScope.launch {
            runCatching {
                repository.toggleFavorite(dummy)
            }.onFailure(Timber::e)
        }
    }
}
