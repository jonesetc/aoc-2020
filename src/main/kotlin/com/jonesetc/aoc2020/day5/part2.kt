@file:JvmName("Part2")
package com.jonesetc.aoc2020.day5

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val seen = inputFile(5)
        .readLines()
        .map { it.replace('F', '0') }
        .map { it.replace('B', '1') }
        .map { it.replace('L', '0') }
        .map { it.replace('R', '1') }
        .map { it.toInt(2) }
        .toSet()

    (0 until 1024)
        .windowed(3)
        .filter { seen.intersect(it) == setOf(it[0], it[2]) }
        .map { it[1] }
        .forEach(::println)
}
