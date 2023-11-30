package com.example.finalprojectpsi.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.finalprojectpsi.data.model.PostData
import com.example.finalprojectpsi.data.model.UserData
import com.example.finalprojectpsi.ui.components.BottomNavigationBar.BottomNavigationBar
import com.example.finalprojectpsi.ui.screens.profile.PostItem
import com.example.finalprojectpsi.ui.theme.Sky500
import com.example.finalprojectpsi.ui.theme.Slate500
import com.example.finalprojectpsi.ui.theme.Slate800
import com.example.finalprojectpsi.ui.theme.Slate950
import com.example.finalprojectpsi.ui.theme.White
import com.example.finalprojectpsi.utils.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    googleAuthClient: GoogleAuthClient,
    homeViewModel: HomeViewModel = viewModel()
) {
    val expanded = remember { mutableStateOf(false) }

    val locationInput = homeViewModel.locationInput.value
    val locations = homeViewModel.locations.value
    val allPosts = homeViewModel.posts.value

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, googleAuthClient) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = Slate950,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo_small),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .width(102.dp)
                        .height(30.dp)
                )
                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = {
                        expanded.value = it
                    },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    TextField(
                        value = locationInput,
                        onValueChange = { },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Slate800,
                            cursorColor = White,
                            textColor = White,
                        ),
                        readOnly = true,
                        textStyle = TextStyle(color = White),
                        placeholder = { Text(text = "Location") }
                    )

                    ExposedDropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        locations.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    homeViewModel.setLocation(item)
                                    expanded.value = false
                                }
                            )
                        }
                    }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                ) {
                    items(allPosts) { post ->
                        HomePostCard(post)
                    }
                }
            }

        }
    }
}

@Composable
fun HomePostCard(post: PostData) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        Image(
            painter = rememberAsyncImagePainter(post.ownerProfilePictureUrl), contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .aspectRatio(ratio = 1f)
                .clip(CircleShape)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "@${post.ownerUserName}",
                style = TextStyle(
                    color = Sky500,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
            Column{
                Text(
                    text = post.title,
                    style = TextStyle(
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = DateUtils.formatDateRelative(post.timeStamp),
                        style = TextStyle(
                            color = Slate500,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "·",
                        style = TextStyle(
                            color = Slate500,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = post.location,
                        style = TextStyle(
                            color = Slate500,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )
                }
            }
            Text(
                text = post.description,
                style = TextStyle(
                    color = White,
                    fontSize = 16.sp
                )
            )

        }
    }
}