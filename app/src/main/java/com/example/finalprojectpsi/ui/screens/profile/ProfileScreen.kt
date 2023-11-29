package com.example.finalprojectpsi.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.data.firebase.GoogleAuthClient
import com.example.finalprojectpsi.ui.components.BottomNavigationBar.BottomNavigationBar
import com.example.finalprojectpsi.ui.components.TopBar.TopBar
import com.example.finalprojectpsi.ui.theme.Indigo600
import com.example.finalprojectpsi.ui.theme.Rose600
import com.example.finalprojectpsi.ui.theme.Slate500
import com.example.finalprojectpsi.ui.theme.Slate800
import com.example.finalprojectpsi.ui.theme.Slate900
import com.example.finalprojectpsi.ui.theme.Slate950
import com.example.finalprojectpsi.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    googleAuthClient: GoogleAuthClient,
    onLogoutClick: () -> Unit,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val name = googleAuthClient.getLoggedInUser()?.name ?: ""
    val userName = googleAuthClient.getLoggedInUser()?.userName ?: ""

    val profileData = profileViewModel.state.value

    Scaffold(
        modifier = Modifier
            .background(color = Color.Black),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Slate900,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Profile",
                        style = TextStyle(
                            color = White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                },
            )
        },
        bottomBar = { BottomNavigationBar(navController, googleAuthClient) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = Slate950,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 30.dp),
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(profileData.profilePictureUrl), contentDescription = null,
                        modifier = Modifier
                            .width(50.dp)
                            .aspectRatio(ratio = 1f)
                            .clip(CircleShape)
                    )
                    Column {
                        Text(
                            text = profileData.name.toString(),
                            style = TextStyle(
                                color = White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                        Text(
                            text = "@${profileData.userName}",
                            style = TextStyle(
                                color = Slate500,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        )
                    }
                }

                Column {
                    Button(
                        onClick = { navController.navigate("edit_profile") },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Slate800
                        )

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        Text(
                            text = "Edit Profile",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                    Button(
                        onClick = onLogoutClick,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Rose600
                        )

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout_icon),
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        Text(
                            text = "Logout",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }

            }
        }

    }
}