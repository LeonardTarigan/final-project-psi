package com.example.finalprojectpsi.ui.screens.edit_profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.ui.components.ImageUploadButton.ImageUploadButton
import com.example.finalprojectpsi.ui.components.TopBar.TopBar
import com.example.finalprojectpsi.ui.theme.Indigo600
import com.example.finalprojectpsi.ui.theme.Slate500
import com.example.finalprojectpsi.ui.theme.Slate800
import com.example.finalprojectpsi.ui.theme.Slate950
import com.example.finalprojectpsi.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController
) {
    val userNameInputState = remember { mutableStateOf("") }
    val nameInputState = remember { mutableStateOf("") }
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri.value = uri
        }

    Scaffold(
        modifier = Modifier
            .background(color = Color.Black),
        topBar = {
            TopBar(title = "Edit Profile")
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = Slate950,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 30.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 20.dp)
                    ) {
                        if (imageUri.value == null)  {
                            Image(
                                painter = painterResource(id = R.drawable.app_logo_small),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Inside,
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(70.dp)
                                    .clip(CircleShape),

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
                                        .width(70.dp)
                                        .height(70.dp)
                                        .clip(CircleShape),

                                    )
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
                                text = "Upload Image",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                        }

                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Username",
                            style = TextStyle(
                                color = Slate500,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        TextField(
                            value = userNameInputState.value,
                            onValueChange = { userNameInputState.value = it },
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
                            text = "Name",
                            style = TextStyle(
                                color = Slate500,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        TextField(
                            value = nameInputState.value,
                            onValueChange = { nameInputState.value = it },
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
                            text = "Email",
                            style = TextStyle(
                                color = Slate500,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        TextField(
                            value = "hatorii@gmail.com",
                            onValueChange = { },
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
                            textStyle = TextStyle(color = Slate500),
                            enabled = false
                        )
                    }
                }

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Indigo600
                    )

                ) {
                    Text(
                        text = "Save Changes",
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