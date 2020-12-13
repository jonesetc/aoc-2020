@file:JvmName("Part1")
package com.jonesetc.aoc2020.day13

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val (timestamp, lines) =
        inputFile(13)
            .readLines()
            .let { (line1, line2) ->
                Pair(
                    line1.toInt(),
                    line2
                        .split(",")
                        .filter { it != "x" }
                        .map { it.toInt() }
                )
            }

    lines
        .map { id ->
            Pair(id, (((timestamp / id) + 1) * id) - timestamp)
        }
        .minByOrNull { it.second }
        ?.also { println(it.first * it.second) }
}
