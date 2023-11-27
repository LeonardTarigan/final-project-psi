package com.example.finalprojectpsi.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.ui.theme.FinalProjectPSITheme
import com.example.finalprojectpsi.ui.theme.Slate950


@Composable
fun ChatScreen(modifier: Modifier = Modifier) {


    FinalProjectPSITheme{
        Surface(
            color = Slate950,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .background(color = Slate950)
        ){
            Box(
                modifier = modifier
                    .background(color = Color(0xff020617))
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 719.dp
                        )
                        .background(color = Color(0xff0f172a)))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(11.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 10.dp,
                            y = 739.dp
                        )
                        .clip(shape = RoundedCornerShape(50.dp))
                        .background(color = Color(0xff1e293b))
                        .padding(
                            horizontal = 25.dp,
                            vertical = 10.dp
                        )
                ) {
                    Text(
                        text = "Type a Message",
                        color = Color(0xff94a3b8),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium)
                    )
                }
                Box(
                    modifier = Modifier
                        .background(color = Color(0xff0f172a)))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 45.dp,
                            y = 22.dp
                        )
                ) {
                    // PUT THE profilePictureUrl HERE
                    Image(
                        painter = painterResource(id = R.drawable.dummy_profile_pic),
                        contentDescription = "Ellipse 3",
                        modifier = Modifier
                            .requiredSize(size = 30.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top)
                    ) {
                        // PUT THE userName HERE
                        Text(
                            text = "Donald Truck",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 16.sp))
                        // PUT THE userId HERE
                        Text(
                            text = "@donaltruk2231",
                            color = Color(0xff64748b),
                            style = TextStyle(
                                fontSize = 12.sp))
                    }
                }
                // this is where user no.1 messages will appeared (we need async between two users)
                val objekModel = ChatModel()
                val messages = objekModel.getMessages()
                LazyColumn(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 119.dp, y = 115.dp)
                ){
                    items(messages){message ->
                        ChatMessage(message = message)
                    }
                }

                // this is where user no.2 messages will appeared (we need async between two users)

                IconButton(
                    onClick = { /* Handle back button click */ },
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 21.dp
                        )
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        modifier = Modifier.size(size = 50.dp),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun ChatPreview() {
    ChatScreen(Modifier)
}

@Composable
fun ChatMessage(message: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xff1e293b))
            .padding(all = 10.dp)
    ) {
        Text(
            text = message,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
    // Time stamp (hour and minute)
    Text(
        text = "11.30 AM",
        color = Color(0xff64748b),
        style = TextStyle(fontSize = 12.sp),
    )
}