package com.example.finalprojectpsi.data.firebase

import android.content.Context
import com.example.finalprojectpsi.data.model.LoginResult
import com.example.finalprojectpsi.data.model.UserData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class EmailPasswordAuthClient(
    private val context: Context
) {
    private val auth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): LoginResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user

            LoginResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        userName = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e

            LoginResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun logout() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getLoggedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            userName = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }
}
