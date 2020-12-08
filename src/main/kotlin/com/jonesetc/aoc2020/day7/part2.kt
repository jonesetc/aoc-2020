@file:JvmName("Part2")
package com.jonesetc.aoc2020.day7

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val mapping = inputFile(7)
        .readLines()
        .map { rawLine ->
            rawLine
                .split(" contain ", ", ")
                .map { it.removeSuffix(".") }
                .map { it.removeSuffix("s") }
                .map { it.removeSuffix(" bag") }
                .filter { color -> color != "no other" }
        }
        .map { parts ->
            Pair(
                parts[0],
                parts
                    .drop(1)
                    .map { it.split(" ") }
                    .map { childParts ->
                        Pair(
                            childParts[0].toInt(),
                            childParts.drop(1).joinToString(" ")
                        )
                    }
            )
        }
        .toMap()

    fun getChildCount(color: String): Int {
        return mapping[color]!!
            .map { (count, child) -> count * getChildCount(child) }
            .sum() + 1
    }

    println(getChildCount("shiny gold") - 1)
}
