package com.example.finalprojectpsi.data.model

data class ChatListData(
    val listChat: List<ChatDataModel>
){
    companion object {
        val dummyInitConvo_1: List<ChatDataModel.Message> = listOf(
            ChatDataModel.Message.initConv
        )
        val dummyInitConvo_2: List<ChatDataModel.Message> = listOf(
            ChatDataModel.Message.secConv
        )
        val chat1 = ChatDataModel(
            messages = dummyInitConvo_1,
            addressee = ChatDataModel.Author.bot
        )
        val chat2 = ChatDataModel(
            messages = dummyInitConvo_2,
            addressee = ChatDataModel.Author.andy
        )
    }
}
