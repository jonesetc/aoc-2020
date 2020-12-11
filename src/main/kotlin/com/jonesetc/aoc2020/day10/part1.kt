@file:JvmName("Part1")
package com.jonesetc.aoc2020.day10

import com.jonesetc.aoc2020.inputFile
import java.util.Collections

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val jolts = inputFile(10)
        .readLines()
        .map(String::toInt)
        .let { it + 0 }
        .sorted()
        .let { it + (it.last() + 3) }

    val differences = jolts
        .windowed(2)
        .map { (prev, curr) -> curr - prev }

    println(Collections.frequency(differences, 1) * Collections.frequency(differences, 3))
}
