@file:JvmName("Part1")
package com.jonesetc.aoc2020.day2

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(2).readLines().map { rawLine ->
        val parts = rawLine.split(" ")
        val requiredRange = parts[0]
            .split("-")
            .map(String::toInt)
            .let { it.first()..it.last() }
        val requiredChar = parts[1][0]
        val password = parts[2]

        password.filter { it == requiredChar }.length in requiredRange
    }.filter { it }.size.also(::println)
}
