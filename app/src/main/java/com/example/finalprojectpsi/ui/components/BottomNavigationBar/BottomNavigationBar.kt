package com.example.finalprojectpsi.ui.components.BottomNavigationBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.finalprojectpsi.data.firebase.GoogleAuthClient
import com.example.finalprojectpsi.ui.theme.Slate900

@Composable
fun BottomNavigationBar(
    navController: NavController,
    googleAuthClient: GoogleAuthClient
) {

    NavigationBar(
        containerColor = Slate900,

    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") }, icon = {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .aspectRatio(ratio = 1f),
            )
        })
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("add_post") },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .aspectRatio(ratio = 1f)
                )
            })
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = {
                val image_url = googleAuthClient.getLoggedInUser()?.profilePictureUrl

                Image(
                    painter = rememberAsyncImagePainter(image_url), contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .aspectRatio(ratio = 1f)
                        .clip(CircleShape)
                )
            })
    }
}
