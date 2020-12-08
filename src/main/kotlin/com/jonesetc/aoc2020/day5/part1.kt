@file:JvmName("Part1")

package com.jonesetc.aoc2020.day5

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(5)
        .readLines()
        .map { it.replace('F', '0') }
        .map { it.replace('B', '1') }
        .map { it.replace('L', '0') }
        .map { it.replace('R', '1') }
        .map { it.toInt(2) }
        .maxOrNull()
        .also(::println)
}
