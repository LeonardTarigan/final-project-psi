package com.example.finalprojectpsi.ui.screens.add_post

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpsi.data.model.PostData
import com.example.finalprojectpsi.data.source.PostRepository
import com.example.finalprojectpsi.data.source.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AddPostViewModel: ViewModel() {
    val inputData = mutableStateOf(PostData())
    val locations = mutableStateOf<List<String>>(emptyList())

    val auth = Firebase.auth
    val db = FirebaseFirestore.getInstance()

    init {
        getLocations()

        auth.currentUser?.run {
            inputData.value = inputData.value.copy(ownerUid = uid)
        }
    }

    fun setTitle(title: String) {
        inputData.value = inputData.value.copy(title = title)
    }

    fun setDescription(description: String) {
        inputData.value = inputData.value.copy(description = description)
    }

    fun setLocation(location: String) {
        inputData.value = inputData.value.copy(location = location)
    }

    private fun getLocations() {
        viewModelScope.launch {
            locations.value = PostRepository().getAllLocations()
        }
    }

    suspend fun addNewPost() {
        val postData = inputData.value

        if (postData.title.isBlank() || postData.description.isBlank()) {
            throw IllegalArgumentException("Title or Description cannot be blank")
        }

        try {
            db.collection("posts")
                .add(postData)
                .await()

            inputData.value = PostData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}