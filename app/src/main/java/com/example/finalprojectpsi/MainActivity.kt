package com.example.finalprojectpsi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectpsi.ui.screens.add_post.AddPostScreen
import com.example.finalprojectpsi.ui.screens.edit_profile.EditProfileScreen
import com.example.finalprojectpsi.ui.screens.login.LoginScreen
import com.example.finalprojectpsi.ui.screens.login.LoginViewModel
import com.example.finalprojectpsi.ui.theme.FinalProjectPSITheme
import com.example.finalprojectpsi.ui.theme.Slate950
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.finalprojectpsi.data.firebase.GoogleAuthClient
import com.example.finalprojectpsi.ui.screens.add_post.AddPostViewModel
import com.example.finalprojectpsi.ui.screens.edit_profile.EditProfileViewModel
import com.example.finalprojectpsi.ui.screens.home.HomeScreen
import com.example.finalprojectpsi.ui.screens.profile.ProfileScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainActivity : ComponentActivity() {
    private val googleAuthClient by lazy {
        GoogleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.indigo_600)
        setContent {
            FinalProjectPSITheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Slate950),
                    color = Slate950,

                    ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            val viewModel = viewModel<LoginViewModel>()
                            val state = viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthClient.getLoggedInUser() != null) {
                                    navController.navigate("home")
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthClient.loginWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onLoginResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.value.isLoginSuccessful) {
                                if (state.value.isLoginSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed-in successfully",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("home")
                                    viewModel.resetState()
                                }
                            }

                            LoginScreen(
                                navController,
                                state.value,
                                onLoginClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthClient.login()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )
                        }

                        composable("home") {
                            HomeScreen(navController, googleAuthClient)
                        }

                        composable("add_post") {
                            val viewModel = viewModel<AddPostViewModel>()

                            AddPostScreen(
                                navController,
                                googleAuthClient,
                                onUploadClicked = {
                                    lifecycleScope.launch {
                                        try {
                                            viewModel.addNewPost()
                                            Toast.makeText(
                                                applicationContext,
                                                "Post Added",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        } catch (e: IllegalArgumentException) {
                                            Toast.makeText(
                                                applicationContext,
                                                e.message.toString(),
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                }
                            )
                        }

                        composable("profile") {
                            ProfileScreen(
                                navController,
                                googleAuthClient,
                                onLogoutClick = {
                                    lifecycleScope.launch {
                                        googleAuthClient.logout()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed Out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.navigate("login")
                                    }
                                }
                            )
                        }

                        composable("edit_profile") {
                            val viewModel = viewModel<EditProfileViewModel>()

                            EditProfileScreen(
                                navController,
                                googleAuthClient,
                                onSaveChangesClick = {
                                    lifecycleScope.launch {
                                        viewModel.saveChanges()
                                        Toast.makeText(
                                            applicationContext,
                                            "Profile Changed Successfully",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.navigate("profile")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
