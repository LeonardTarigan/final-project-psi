package com.example.finalprojectpsi.data.source

import android.util.Log
import com.example.finalprojectpsi.data.model.UserData
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
    var locations = emptyList<String>()

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
}