package com.example.finalprojectpsi.ui.screens.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpsi.data.model.UserData
import com.example.finalprojectpsi.data.source.UserRepository
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
            state.value = UserRepository().getUserData()
        }
    }
}
