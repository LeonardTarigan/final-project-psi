package com.example.finalprojectpsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectpsi.data.model.ChatDataModel
import com.example.finalprojectpsi.ui.screens.chat.ChatScreen
import com.example.finalprojectpsi.ui.screens.chat.ChatViewModel
import com.example.finalprojectpsi.ui.screens.login.LoginScreen

import com.example.finalprojectpsi.ui.screens.register.RegisterScreen

import com.example.finalprojectpsi.ui.theme.FinalProjectPSITheme
import com.example.finalprojectpsi.ui.theme.Indigo600
import com.example.finalprojectpsi.ui.theme.Slate950

class MainActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.indigo_600)

        setContent {
            val conversation = viewModel.conversation.collectAsState()

            FinalProjectPSITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Slate950),
                    color = Slate950,


                    ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController)
                        }

                        composable("register") {
                            RegisterScreen(navController)
                        }

                        composable("chatId") {
                            // This is still dummy data
                            ChatScreen(
                                model = ChatDataModel(
                                    messages = conversation.value,
                                    addressee = ChatDataModel.Author.bot
                                ),
                                onSendChatClickListener = { msg -> viewModel.sendChat(msg) },
                                modifier = Modifier,
                                navController = navController
                            )
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = Color.Black
    )
}