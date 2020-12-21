@file:JvmName("Part1")
package com.jonesetc.aoc2020.day16

import com.jonesetc.aoc2020.inputFile

fun parseRules(raw: String): Map<String, List<IntRange>> {
    return raw
        .split("\n")
        .map { it.split(": ") }
        .map { (field, rawRules) ->
            rawRules
                .split(" or ")
                .map { rawRange ->
                    rawRange
                        .split("-")
                        .map(String::toInt)
                }
                .map { rangeParts -> IntRange(rangeParts.first(), rangeParts.last()) }
                .let { ranges -> Pair(field, ranges) }
        }
        .toMap()
}

fun parseTicket(raw: String): List<Int> {
    return raw
        .split(",")
        .map(String::toInt)
}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val parts = inputFile(16)
        .readText()
        .trim()
        .split("\n\n")

    val ruleMap = parseRules(parts[0])

    parts[2]
        .split("\n")
        .drop(1)
        .map(::parseTicket)
        .flatten()
        .filter { value ->
            !ruleMap
                .values
                .any { ranges ->
                    ranges
                        .any { range ->
                            value in range
                        }
                }
        }
        .sum()
        .also(::println)
}
