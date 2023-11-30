package com.example.finalprojectpsi.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpsi.data.model.PostData
import com.example.finalprojectpsi.data.source.PostRepository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val locationInput = mutableStateOf<String>("")
    val locations = mutableStateOf<List<String>>(emptyList())
    val posts = mutableStateOf<List<PostData>>(emptyList())

    init {
        getLocations()
        getPosts()
    }

    fun setLocation(location: String) {
        locationInput.value = location

        getPosts()
    }

    private fun getLocations() {
        viewModelScope.launch {
            locations.value = PostRepository().getAllLocations()
        }
    }

    private fun getPosts() {
        viewModelScope.launch {
            posts.value = when(locationInput.value) {
                "" -> PostRepository().getAllPosts()
                else -> PostRepository().getPostsByLocation(locationInput.value)
            }
        }
    }
}