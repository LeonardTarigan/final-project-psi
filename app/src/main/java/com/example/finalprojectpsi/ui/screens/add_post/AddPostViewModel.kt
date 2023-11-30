package com.example.finalprojectpsi.ui.screens.add_post

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpsi.data.model.PostData
import com.example.finalprojectpsi.data.source.PostRepository
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
            viewModelScope.launch {
                try {
                    val userDoc = db.collection("users").document(uid).get().await()
                    val userName = userDoc.getString("userName")
                    val profilePictureUrl = userDoc.getString("profilePictureUrl")

                    inputData.value = inputData.value.copy(ownerUid = uid, ownerUserName = userName!!, ownerProfilePictureUrl = profilePictureUrl!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
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

            setTitle("")
            setDescription("")
            setLocation("")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}