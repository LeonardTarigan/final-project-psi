package com.example.finalprojectpsi.ui.screens.chatList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.finalprojectpsi.R
import com.example.finalprojectpsi.data.model.ChatDataModel
import com.example.finalprojectpsi.data.model.ChatListData
import com.example.finalprojectpsi.ui.theme.Slate950
import java.util.EventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(model: ChatListData,

                   modifier: Modifier){
    Surface(
        color = Slate950,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(color = Slate950)
    ){
        ConstraintLayout(
            modifier = modifier.fillMaxSize()
        ) {
            val (searchBar , messageList, navBar) = createRefs()

            val listState = rememberLazyListState()
            LaunchedEffect(model.listChat.size) {
                listState.animateScrollToItem(model.listChat.size)
            }

            // this is the Search Bar (Top Section)
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(searchBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            // this is the List of Messages (Middle Section)
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(messageList) {
                        top.linkTo(searchBar.bottom)
                        bottom.linkTo(navBar.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(16.dp)
            ) {
                // this emit every object of ChatDataModel that has all messages and Author
                items(model.listChat) { item ->
                    MessageList(item)
                }
            }

            // this is the Navigation Bar (Bottom Section)
            NavBar(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(navBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

        }
    }
}

@Composable
fun SearchBar(modifier: Modifier){
    var searchBoxValue by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier
        .background(color = Color(0xff020617))
        .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.app_logo_small),
            contentDescription = "Logo Los",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(100.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(11.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(50.dp))
                .background(color = Color(0xff1e293b))
                .padding(horizontal = 25.dp, vertical = 10.dp)
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search Symbol",
                tint = Color(0xff94a3b8),
                modifier = Modifier.size(30.dp)
            )
            // Replace it with textField to search name by using the ChatListData.kt
            Text(
                text = "Search",
                color = Color(0xff94a3b8),
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun MessageList(chat: ChatDataModel){

}



@Composable
fun NavBar(modifier: Modifier){
    Divider(color = Color.White, thickness = 1.dp)
    Box(
        modifier = modifier
        .background(color = Color(0xff020617))
        .padding(
            horizontal = 20.dp,
            vertical = 15.dp
        )
    ){
        Row (
            horizontalArrangement = Arrangement.spacedBy(105.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
        ) {
            IconButton(
                onClick = { /* Handle back button click */ }
            ) {
                Icon(
                    Icons.Default.Home,
                    modifier = Modifier.size(size = 100.dp),
                    contentDescription = "Home",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { /* Handle back button click */ }
            ) {
                Icon(
                    Icons.Default.AddCircle,
                    modifier = Modifier.size(size = 100.dp),
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Profile pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .aspectRatio(4f / 4f)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(Modifier)
}

@Preview(showBackground = true)
@Composable
private fun NavBarPreview() {
    NavBar(Modifier)
}

