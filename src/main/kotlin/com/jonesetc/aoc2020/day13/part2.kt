@file:JvmName("Part2")
package com.jonesetc.aoc2020.day13

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val lines =
        inputFile(13)
            .readLines()
            .last()
            .split(",")
            .map { raw ->
                when (raw) {
                    "x" -> null
                    else -> raw.toInt()
                }
            }

    var minPossible = 0L
    var currentMultiple = 1L

    for ((index, id) in lines.withIndex()) {
        if (id == null) {
            continue
        }

        while ((minPossible + index) % id != 0L) {
            minPossible += currentMultiple
        }

        // the input is nice and all primes
        // otherwise we'd need to actually compute LCM here
        currentMultiple *= id
    }

    println(minPossible)
}
