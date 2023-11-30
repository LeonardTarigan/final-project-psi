package com.example.finalprojectpsi.data.model

import java.sql.Timestamp
import java.util.Date


data class PostData(
    val documentId: String = "",
    val title: String = "",
    val description: String = "",
    val ownerUid: String = "",
    val ownerUserName: String = "",
    val ownerProfilePictureUrl: String = "",
    val isResolved: Boolean = false,
    val location: String = "",
    val timeStamp: Date = Timestamp.from(Date().toInstant())
)
