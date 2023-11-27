package com.example.finalprojectpsi.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.ui.theme.Black
import com.example.finalprojectpsi.ui.theme.FinalProjectPSITheme
import com.example.finalprojectpsi.ui.theme.Sky500
import com.example.finalprojectpsi.ui.theme.Slate400
import com.example.finalprojectpsi.ui.theme.Slate500
import com.example.finalprojectpsi.ui.theme.Slate700
import com.example.finalprojectpsi.ui.theme.Slate800
import com.example.finalprojectpsi.ui.theme.Slate900
import com.example.finalprojectpsi.ui.theme.Slate950
import com.example.finalprojectpsi.ui.theme.White
import com.example.finalprojectpsi.ui.theme.Yellow400
import com.example.finalprojectpsi.ui.theme.mainFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterScreen(
    navController: NavController
) {
    val emailInputState = remember {
        mutableStateOf("")
    }
    val passwordInputState = remember {
        mutableStateOf("")
    }

    FinalProjectPSITheme {
        Surface(
            color = Slate950,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .background(color = Slate950)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentHeight()
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Min)
                    .background(color = Slate900, shape = RoundedCornerShape(size = 20.dp))
                    .padding(30.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(58.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo_small),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .width(102.dp)
                            .height(30.dp)
                    )

                    Text(
                        text = "Register New Account",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(700),
                            color = Color.White,
                        )
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    TextField(
                        value = emailInputState.value,
                        onValueChange = { emailInputState.value = it },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Slate800,
                            cursorColor = White,
                            textColor = White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(color = White),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email, contentDescription = "Email Icon",
                                tint = Slate400
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Email",
                                style = TextStyle(
                                    color = Slate400,
                                    fontWeight = FontWeight(600)
                                )
                            )
                        }
                    )
                    TextField(
                        value = passwordInputState.value,
                        onValueChange = { passwordInputState.value = it },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Slate800,
                            cursorColor = White,
                            textColor = White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(color = White),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Password Icon",
                                tint = Slate400
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Password",
                                style = TextStyle(
                                    color = Slate400,
                                    fontWeight = FontWeight(600)
                                )
                            )
                        },
                        visualTransformation = PasswordVisualTransformation()
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Sky500
                    )

                ) {
                    Text(
                        text = "Register",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color.White
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        color = Slate500, modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = "or",
                        style = TextStyle(
                            fontWeight = FontWeight(500),
                            color = Slate400,
                        )
                    )
                    Divider(
                        color = Slate500, modifier = Modifier
                            .weight(1f)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = White
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Register with Google",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Black
                            )
                        )
                    }
                    ClickableText(
                        onClick = {
                                  navController.navigate("login")
                        },
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = White)) {
                                append("Don’t have an account? ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Sky500,
                                    textDecoration = TextDecoration.Underline,
                                    fontWeight = FontWeight(700)
                                )
                            ) {
                                append("Register")
                                addStringAnnotation("clickable", "Register", 0, length)
                            }
                        }
                    )
                }
            }
        }

    }
}