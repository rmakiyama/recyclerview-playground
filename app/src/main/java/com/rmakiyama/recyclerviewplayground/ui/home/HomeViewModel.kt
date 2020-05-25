package com.rmakiyama.recyclerviewplayground.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rmakiyama.recyclerviewplayground.data.DummyRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    repository: DummyRepository
) : ViewModel() {

    val dummies = repository.dummies().asLiveData()
}
