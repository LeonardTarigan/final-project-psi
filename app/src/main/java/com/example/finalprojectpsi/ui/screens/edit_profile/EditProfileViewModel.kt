package com.example.finalprojectpsi.ui.screens.edit_profile

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpsi.data.model.UserData
import com.example.finalprojectpsi.data.source.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EditProfileViewModel : ViewModel() {
    val userData = mutableStateOf(UserData())
    val imageUri = mutableStateOf<Uri?>(null)
    val bitmap = mutableStateOf<Bitmap?>(null)

    init {
        getData()
    }

    fun setUserName(userName: String) {
        userData.value = userData.value.copy(userName = userName)
    }

    fun setName(name: String) {
        userData.value = userData.value.copy(name = name)
    }

    private fun getData() {
        viewModelScope.launch {
            userData.value = UserRepository().getUserData()
        }
    }

    fun saveChanges() {
        // Create a UserData object with the updated values
        val updatedUserData = UserData(
            userName = userData.value.userName,
            name = userData.value.name,
            profilePictureUrl = userData.value.profilePictureUrl
        )

//        uploadImageToFirebaseStorage()

        updateFirestoreRecord(updatedUserData)
    }

    private fun updateFirestoreRecord(userData: UserData) {
        viewModelScope.launch {
            UserRepository().updateUserData(userData, imageUri.value)
        }
    }

    private fun uploadImageToFirebaseStorage() {
        viewModelScope.launch {
           if (imageUri.value != null) {
               UserRepository().uploadImage(imageUri.value!!)
           }
        }


    }

}

