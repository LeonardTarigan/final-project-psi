package com.example.finalprojectpsi.ui.screens.add_post

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.data.firebase.GoogleAuthClient
import com.example.finalprojectpsi.ui.components.BottomNavigationBar.BottomNavigationBar
import com.example.finalprojectpsi.ui.components.TopBar.TopBar
import com.example.finalprojectpsi.ui.theme.Indigo600
import com.example.finalprojectpsi.ui.theme.Sky500
import com.example.finalprojectpsi.ui.theme.Slate400
import com.example.finalprojectpsi.ui.theme.Slate500
import com.example.finalprojectpsi.ui.theme.Slate700
import com.example.finalprojectpsi.ui.theme.Slate800
import com.example.finalprojectpsi.ui.theme.Slate900
import com.example.finalprojectpsi.ui.theme.Slate950
import com.example.finalprojectpsi.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen(
    navController: NavController,
    googleAuthClient: GoogleAuthClient,
    onUploadClicked: () -> Unit,
    addPostViewModel: AddPostViewModel = viewModel()
) {

    val input = addPostViewModel.inputData.value
    val locations = addPostViewModel.locations.value
    val context = LocalContext.current

    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val expanded = remember { mutableStateOf(false) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri.value = uri
        }

    Scaffold(
        modifier = Modifier
            .background(color = Color.Black),
        topBar = {
            TopBar(title = "Add New Post", backRoute = "home", navController)
        },
        bottomBar = {
            BottomNavigationBar(navController, googleAuthClient)
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = Slate950,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 30.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Phone Number (628xx)",
                            style = TextStyle(
                                color = Slate500,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        TextField(
                            value = input.ownerPhoneNumber,
                            onValueChange = { addPostViewModel.setOwnerPhoneNumber(it) },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Slate800,
                                cursorColor = White,
                                textColor = White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            textStyle = TextStyle(color = White),
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Title",
                            style = TextStyle(
                                color = Slate500,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        TextField(
                            value = input.title,
                            onValueChange = { addPostViewModel.setTitle(it) },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Slate800,
                                cursorColor = White,
                                textColor = White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            textStyle = TextStyle(color = White),
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Description",
                            style = TextStyle(
                                color = Slate500,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        TextField(
                            value = input.description,
                            onValueChange = { addPostViewModel.setDescription(it) },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Slate800,
                                cursorColor = White,
                                textColor = White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                            ),
                            textStyle = TextStyle(color = White),
                        )
                    }

                    ExposedDropdownMenuBox(
                        expanded = expanded.value,
                        onExpandedChange = {
                            expanded.value = it
                        },
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        TextField(
                            value = input.location,
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
                            placeholder = { Text(text = "Location")}
                        )

                        ExposedDropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false }
                        ) {
                            locations.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        addPostViewModel.setLocation(item)
                                        expanded.value = false
                                    }
                                )
                            }
                        }
                    }

                    Button(
                        onClick = { launcher.launch("image/*") },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Slate800
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.image_icon),
                            contentDescription = "Image Icon",
                            modifier = Modifier
                                .size(size = 30.dp)
                                .padding(end = 5.dp),
                            tint = White
                        )
                        Text(
                            text = "Add Image",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }

                    imageUri.value?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.value = MediaStore.Images
                                .Media.getBitmap(context.contentResolver, it)
                        } else {
                            val source = ImageDecoder.createSource(context.contentResolver, it)
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }

                        bitmap.value?.let { btm ->
                            Image(
                                bitmap = btm.asImageBitmap(),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .fillMaxWidth()
                                    .aspectRatio(ratio = 1f),

                                )
                        }
                    }
                }



                Button(
                    onClick = onUploadClicked,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Indigo600
                    )

                ) {
                    Text(
                        text = "Upload Post",
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