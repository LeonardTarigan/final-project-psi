package com.example.finalprojectpsi.data.source

import android.util.Log
import com.example.finalprojectpsi.data.model.PostData
import com.example.finalprojectpsi.data.model.UserData
import com.example.finalprojectpsi.utils.StringUtils
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class PostRepository {
    val db = FirebaseFirestore.getInstance()
    val auth = Firebase.auth

    suspend fun getAllLocations(): List<String> {
        try {
            val documentSnapshot: DocumentSnapshot = db.collection("locations")
                .document("ub")
                .get()
                .await()

            if (documentSnapshot.exists()) {
                val data = documentSnapshot.data
                if (data != null) {
                    // Assuming the values are stored under a field called "values"
                    val values = data["locations"] as? List<String>
                    if (values != null) {
                        return values
                    }
                }
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("Error", "getAllLocations $e")
        }

        // Return an empty list if there's an error or no data
        return emptyList()
    }

    suspend fun getAllPosts(): List<PostData> {
        try {
            val querySnapshot: QuerySnapshot = db.collection("posts")
                .get()
                .await()

            return querySnapshot.documents.mapNotNull { documentSnapshot ->
                val postData = documentSnapshot.toObject(PostData::class.java)
                postData?.copy(documentId = documentSnapshot.id)
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("Error", "getAllPosts $e")
        }

        return emptyList()
    }

    suspend fun getAllUserPosts(): List<PostData> {
        val currentUserUid = auth.currentUser?.uid

        if (currentUserUid != null) {
            try {
                val querySnapshot: QuerySnapshot = db.collection("posts")
                    .whereEqualTo("ownerUid", currentUserUid)
                    .get()
                    .await()

                return querySnapshot.documents.mapNotNull { documentSnapshot ->
                    val postData = documentSnapshot.toObject(PostData::class.java)
                    postData?.copy(documentId = documentSnapshot.id)
                }
            } catch (e: FirebaseFirestoreException) {
                Log.d("Error", "getAllUserPosts $e")
            }
        }

        return emptyList()
    }

    suspend fun getPostsByLocation(location: String): List<PostData> {
        try {
            val querySnapshot = if (location.isNotBlank()) {
                db.collection("posts")
                    .whereEqualTo("location", location)
                    .get()
                    .await()
            } else {
                // If location is blank, fetch all posts
                db.collection("posts")
                    .get()
                    .await()
            }

            return querySnapshot.documents.mapNotNull { documentSnapshot ->
                val postData = documentSnapshot.toObject(PostData::class.java)
                postData?.copy(documentId = documentSnapshot.id)
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("Error", "getPostsByLocation $e")
        }

        return emptyList()
    }
}