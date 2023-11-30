package com.example.finalprojectpsi.data.firebase

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.data.model.LoginResult
import com.example.finalprojectpsi.data.model.UserData
import com.example.finalprojectpsi.utils.StringUtils
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException


class GoogleAuthClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth
    private val db = FirebaseFirestore.getInstance()

    suspend fun login(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildLoginRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }

        return result?.pendingIntent?.intentSender
    }

    suspend fun logout() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e

        }
    }

    fun getLoggedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            userName = StringUtils.joinArrayWithFourDigitRandom(displayName ?: ""),
            profilePictureUrl = photoUrl?.toString(),
            name = displayName,
            email = email
        )
    }

    suspend fun loginWithIntent(intent: Intent): LoginResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user

            user?.run {
                val userExists = db.collection("users").document(uid).get().await().exists()

                if (!userExists) {
                    val userData = UserData(
                        userId = uid,
                        userName = StringUtils.joinArrayWithFourDigitRandom(displayName ?: ""),
                        profilePictureUrl = photoUrl?.toString(),
                        name = displayName,
                        email = email
                    )
                    db.collection("users").document(uid).set(userData).await()
                }
            }

            LoginResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        userName = StringUtils.joinArrayWithFourDigitRandom(displayName ?: ""),
                        profilePictureUrl = photoUrl?.toString(),
                        name = displayName,
                        email = email

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

    private fun buildLoginRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}