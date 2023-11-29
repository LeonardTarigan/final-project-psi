package com.example.finalprojectpsi.utils

import kotlin.random.Random

object StringUtils {
    fun joinArrayWithFourDigitRandom(input: String): String {
        val usernameWithoutSpaces = input.replace("\\s".toRegex(), "")
        val random4DigitNumber = Random.nextInt(1000, 10000)

        return "${usernameWithoutSpaces.lowercase()}$random4DigitNumber"
    }
}