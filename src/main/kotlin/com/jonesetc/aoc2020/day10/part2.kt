@file:JvmName("Part2")
package com.jonesetc.aoc2020.day10

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val jolts = inputFile(10)
        .readLines()
        .map(String::toInt)
        .let { it + 0 }
        .sorted()
        .let { it + (it.last() + 3) }

    val paths = MutableList(jolts.size) { index ->
        when (index) {
            0 -> 1L
            else -> 0L
        }
    }

    for (index in jolts.indices) {
        (1..3)
            .filter { offset -> index + offset in jolts.indices }
            .filter { offset -> jolts[index + offset] - jolts[index] <= 3 }
            .forEach { offset -> paths[index + offset] += paths[index] }
    }

    println(paths.last())
}
