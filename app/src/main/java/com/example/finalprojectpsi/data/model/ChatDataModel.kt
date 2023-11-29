package com.example.finalprojectpsi.data.model

data class ChatDataModel(
    val messages: List<Message>,
    val addressee: Author,
) {
    data class Message(
        val text: String,
        val author: Author,
    ) {
        val isFromMe: Boolean
            get() = author.id == MY_ID

        companion object {
            val initConv = Message(
                text = "Hi there, how you doing?",
                author = Author.bot
            )
            val secConv = Message(
                text = "ANDY HEREEE!!!",
                author = Author.andy
            )
        }
    }

    data class Author(
        val id: String,
        val name: String
    ) {
        companion object {
            val andy = Author("2", "Andy")
            val bot = Author("1", "Bot")
            val me = Author(MY_ID, "Me")
        }
    }

    companion object {
        const val MY_ID = "-1"
    }
}
