package com.example.finalprojectpsi.ui.screens.chat

class ChatModel(){
    private val messages = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
        "Duis aute irure dolor in reprehenderit in voluptate velit."
    )

    fun getMessages(): List<String>{
        return messages
    }
}