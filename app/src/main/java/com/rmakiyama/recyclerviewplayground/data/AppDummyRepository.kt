package com.rmakiyama.recyclerviewplayground.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rmakiyama.recyclerviewplayground.model.User
import javax.inject.Inject
import kotlin.random.Random

class AppDummyRepository @Inject constructor() : DummyRepository {

    // ローカルDBの代替
    private var data = listOf(
        User(), User(), User(), User(), User(), User(), User(),
        User(), User(), User(), User(), User(), User(), User()
    )

    private val _dummies = MutableLiveData<List<User>>(listOf())
    override val dummies: LiveData<List<User>> get() = _dummies

    init {
        loadData()
    }

    override suspend fun toggleFavorite(user: User) {
        updateLocalFavorite(user.id)
    }

    override suspend fun changeUser(user: User) {
        data = data.map { data ->
            if (data.id == user.id) User() else data
        }
        loadData()
    }

    override suspend fun changePhoto(user: User) {
        val newImageUrl = "https://randomuser.me/api/portraits/men/${Random.nextInt(0, 50)}.jpg"
        data = data.map { data ->
            if (data.id == user.id) data.copy(imageUrl = newImageUrl) else data
        }
        loadData()
    }

    // ローカルDBを更新
    private fun updateLocalFavorite(id: String) {
        data = data.map { dummy ->
            if (dummy.id == id) dummy.copy(isFavorite = !dummy.isFavorite) else dummy
        }
        loadData()
    }

    private fun loadData() {
        _dummies.value = data
    }
}
