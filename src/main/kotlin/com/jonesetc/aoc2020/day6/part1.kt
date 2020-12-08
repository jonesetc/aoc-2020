@file:JvmName("Part1")
package com.jonesetc.aoc2020.day6

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(6)
        .readText()
        .trim()
        .split("\n\n")
        .map { group ->
            group
                .replace("\n", "")
                .toCharArray()
                .toSet()
                .size
        }
        .sum()
        .also(::println)
}
