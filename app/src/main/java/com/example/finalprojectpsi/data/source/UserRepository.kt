package com.example.finalprojectpsi.data.source

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.finalprojectpsi.data.model.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class UserRepository {
    val db = FirebaseFirestore.getInstance()
    val auth = Firebase.auth
    var profile = UserData()

    suspend fun getUserData(): UserData {
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

    suspend fun updateUserData(userData: UserData) {
        try {
            val documentReference = db.collection("users").document(userData.userId)

            documentReference.update(
                "userName", userData.userName,
                "name", userData.name,
            ).await()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//     fun uploadImage(imageUri: Uri) {
//        imageUri.let { uri ->
//            val storageRef = FirebaseStorage.getInstance().reference
//            val userId = auth.currentUser?.uid
//            val imageRef = storageRef.child("images/$userId.jpg")
//            val uploadTask = imageRef.putFile(uri)
//
//            uploadTask.addOnSuccessListener {
//
//            }.addOnFailureListener {
//            }
//
//        }
//    }
}