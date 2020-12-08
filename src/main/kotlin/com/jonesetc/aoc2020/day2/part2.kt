@file:JvmName("Part2")
package com.jonesetc.aoc2020.day2

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(2).readLines().map { rawLine ->
        val parts = rawLine.split(" ")
        val possibleIndices = parts[0]
            .split("-")
            .map(String::toInt)
            .let { Pair(it.first() - 1, it.last() - 1) }
        val requiredChar = parts[1][0]
        val password = parts[2]

        (password[possibleIndices.first] == requiredChar) xor (password[possibleIndices.second] == requiredChar)
    }.filter { it }.size.also(::println)
}
