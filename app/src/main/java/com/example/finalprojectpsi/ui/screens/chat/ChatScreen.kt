package com.example.finalprojectpsi.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.data.model.ChatDataModel
import com.example.finalprojectpsi.ui.theme.FinalProjectPSITheme
import com.example.finalprojectpsi.ui.theme.Slate950


// (Reference)
// https://medium.com/@meytataliti/building-a-simple-chat-app-with-jetpack-compose-883a240592d4
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(model: ChatDataModel,
               onSendChatClickListener: (String) -> Unit,
               modifier: Modifier,
               navController: NavController
) {
    Surface(
        color = Slate950,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(color = Slate950)
    ){
        ConstraintLayout(modifier = modifier.fillMaxSize()) {
            val (profil ,messages, input) = createRefs()

            val listState = rememberLazyListState()
            LaunchedEffect(model.messages.size) {
                listState.animateScrollToItem(model.messages.size)
            }

            // this is the top bar
            ChatProfile(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(profil) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            // this is the chat bubbles
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(messages) {
                        top.linkTo(profil.bottom)
                        bottom.linkTo(input.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(16.dp)
            ) {
                items(model.messages) { item ->
                    ChatItem(item)
                }
            }

            // this is the chat input BOX
            ChatInput(
                onSendChatClickListener,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(input) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

@Preview
@Composable
private fun ChatPreview(){
    val navController = rememberNavController()

    FinalProjectPSITheme {
        ChatScreen(
            model = ChatDataModel(
                messages = listOf(
                    ChatDataModel.Message(
                        "Hi Tree, How you doing?",
                        ChatDataModel.Author("0", "Branch")
                    ),
                    ChatDataModel.Message(
                        "Hi Branch, good. You?",
                        ChatDataModel.Author("-1", "Tree"))
                ),
                addressee = ChatDataModel.Author("0", "Branch")
            ),
            onSendChatClickListener = {},
            modifier = Modifier,
            navController = navController
        )
    }
}

@Composable
private fun ChatItem(message: ChatDataModel.Message){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Box(
            modifier = Modifier
                .align(if (message.isFromMe) Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (message.isFromMe) 48f else 0f,
                        bottomEnd = if (message.isFromMe) 0f else 48f
                    )
                )
                .background(color = Color(0xff1e293b))
                .padding(16.dp)
        ) {
            Text(
                text = message.text,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatInput(
    onSendChatClickListener: (String) -> Unit,
    modifier: Modifier
){
    var chatBoxValue by remember { mutableStateOf(TextFieldValue("")) }
    Box(modifier = modifier
        .background(color = Color(0xff0f172a))

    ){
        Row(modifier = modifier
            .padding(16.dp)) {
            TextField(
                value = chatBoxValue,
                onValueChange = { newText ->
                    chatBoxValue = newText
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .height(50.dp)
                    .background(Color(0xff1e293b)),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(text = "Type something")
                }
            )
            IconButton(
                onClick = {
                    val msg = chatBoxValue.text
                    if (msg.isBlank()) return@IconButton
                    onSendChatClickListener(chatBoxValue.text)
                    chatBoxValue = TextFieldValue("")
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.White)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Send",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatProfile(
    modifier: Modifier){
    Box(modifier = modifier
        .background(color = Color(0xff0f172a))

    ){
        Row (
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(8.dp)
        ) {
            IconButton(
                onClick = { /* Handle back button click */ }
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    modifier = Modifier.size(size = 50.dp),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            // PUT THE profilePictureUrl HERE
            Image(
                painter = painterResource(id = R.drawable.dummy_profile_pic),
                contentDescription = "Profile Pic",
                modifier = Modifier.size(50.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top)
            ) {
                // PUT THE userName HERE
                Text(
                    text = "Donald Truck",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
                // PUT THE userId HERE
                Text(
                    text = "@donaltruk2231",
                    color = Color(0xff64748b),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}