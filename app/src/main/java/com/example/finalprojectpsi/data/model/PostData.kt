package com.example.finalprojectpsi.data.model

import java.time.Instant


data class PostData(
    val title: String = "",
    val description: String = "",
    val ownerUid: String = "",
    val isResolved: Boolean = false,
    val location: String = "",
    val timeStamp: Instant = Instant.now()
)
