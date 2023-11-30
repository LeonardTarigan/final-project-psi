package com.example.finalprojectpsi.utils

import java.util.*

object DateUtils {

    fun formatDateRelative(date: Date): String {
        val now = Date().time
        val timeDifference = now - date.time
        val seconds = timeDifference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = days / 30
        val years = days / 365

        return when {
            seconds < 60 -> "$seconds seconds ago"
            minutes < 60 -> "$minutes minutes ago"
            hours < 24 -> "$hours hours ago"
            days < 7 -> "$days days ago"
            weeks < 4 -> "$weeks weeks ago"
            months < 12 -> "$months months ago"
            else -> "$years years ago"
        }
    }
}