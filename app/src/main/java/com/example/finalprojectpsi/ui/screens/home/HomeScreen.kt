package com.example.finalprojectpsi.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.finalprojectpsi.data.firebase.GoogleAuthClient
import com.example.finalprojectpsi.ui.components.BottomNavigationBar.BottomNavigationBar
import com.example.finalprojectpsi.ui.theme.Slate950

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    googleAuthClient: GoogleAuthClient
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController, googleAuthClient) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = Slate950,
        ) {
            Text(text = "Home")

        }
    }
}