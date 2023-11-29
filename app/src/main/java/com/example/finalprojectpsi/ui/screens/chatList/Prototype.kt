package com.example.finalprojectpsi.ui.screens.chatList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectpsi.R

@Composable
fun Prototype(modifier: Modifier){
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 800.dp)
            .background(color = Color(0xff020617))
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(11.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 10.dp,
                    y = 76.dp
                )
                .requiredWidth(width = 340.dp)
                .clip(shape = RoundedCornerShape(50.dp))
                .background(color = Color(0xff1e293b))
                .padding(
                    horizontal = 25.dp,
                    vertical = 10.dp
                )
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search Symbol",
                modifier = Modifier
                    .requiredSize(size = 20.dp)
                    .border(border = BorderStroke(2.dp, Color(0xff94a3b8)))
            )
            Text(
                text = "Search",
                color = Color(0xff94a3b8),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 126.dp
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .requiredWidth(width = 360.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 10.dp,
                            vertical = 20.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(size = 30.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.White)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) { append("@donaltruk2231") }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color(0xff64748b),
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) { append(" ") }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color(0xff64748b),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) { append("·") }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color(0xff64748b),
                                            fontSize = 12.sp
                                        )
                                    ) { append(" 12 minutes ago") }
                                })
                        }
                        Text(
                            text = "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                            color = Color(0xff64748b),
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.app_logo_small),
            contentDescription = "Logo Los",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 128.dp,
                    y = 20.dp
                )
                .requiredWidth(width = 103.dp)
                .requiredHeight(height = 30.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .requiredWidth(width = 360.dp)
                .background(color = Color(0xff020617))
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                )
        ) {
            IconButton(
                onClick = { },
                content = {},
                modifier = Modifier
                    .requiredSize(size = 25.dp)
                    .background(color = Color.White)
            )
            Icon(
                Icons.Default.Home,
                contentDescription = "Vector",
                modifier = Modifier
                    .requiredSize(size = 25.dp)
                    .background(color = Color.White)
            )
            Image(
                painter = painterResource(id = R.drawable.dummy_profile_pic),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .requiredSize(size = 35.dp)
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun ChatListPreview() {
    Prototype(Modifier)
}
