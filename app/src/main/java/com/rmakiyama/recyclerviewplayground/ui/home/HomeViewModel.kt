package com.rmakiyama.recyclerviewplayground.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.recyclerviewplayground.data.DummyRepository
import com.rmakiyama.recyclerviewplayground.model.User
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: DummyRepository
) : ViewModel() {

    val dummies = repository.dummies

    fun toggleFavorite(user: User) {
        viewModelScope.launch {
            runCatching {
                repository.toggleFavorite(user)
            }.onFailure(Timber::e)
        }
    }
}
