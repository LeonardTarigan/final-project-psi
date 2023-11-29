package com.example.finalprojectpsi.ui.screens.chatList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
                .constrainAs(navBar){
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

}

@Composable
fun MessageList(chat: ChatDataModel){

}

@Composable
fun NavBar(modifier: Modifier){

}