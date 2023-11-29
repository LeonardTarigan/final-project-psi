package com.example.finalprojectpsi.ui.screens.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpsi.data.model.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



class ProfileViewModel: ViewModel() {
    val state = mutableStateOf(UserData())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getUserProfile()
        }
    }
}

suspend fun getUserProfile(): UserData {
    val db = FirebaseFirestore.getInstance()
    val auth = Firebase.auth
    var profile = UserData()

    auth.currentUser?.run {
        try {
            val querySnapshot: QuerySnapshot = db.collection("users")
                .whereEqualTo("userId", uid)
                .get()
                .await()

            querySnapshot.run {
                val documentSnapshot = documents[0]
                profile = documentSnapshot.toObject(UserData::class.java)!!
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("Error", "getUserProfile $e")
        }
    }

    return profile
}