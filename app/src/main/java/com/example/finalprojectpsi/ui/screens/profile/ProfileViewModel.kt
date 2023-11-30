package com.example.finalprojectpsi.ui.screens.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpsi.data.model.PostData
import com.example.finalprojectpsi.data.model.UserData
import com.example.finalprojectpsi.data.source.PostRepository
import com.example.finalprojectpsi.data.source.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



class ProfileViewModel: ViewModel() {
    val userData = mutableStateOf(UserData())
    val userPosts = mutableStateOf<List<PostData>>(emptyList())

    init {
        getUser()
        getPosts()
    }

    private fun getUser() {
        viewModelScope.launch {
            userData.value = UserRepository().getUserData()
        }
    }

    private fun getPosts() {
        viewModelScope.launch {
            userPosts.value = PostRepository().getAllUserPosts()
        }
    }
}
