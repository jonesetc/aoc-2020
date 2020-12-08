@file:JvmName("Part2")
package com.jonesetc.aoc2020.day6

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(6)
        .readText()
        .trim()
        .split("\n\n")
        .map { group ->
            group
                .split("\n")
                .map { person ->
                    person
                        .toCharArray()
                        .toSet()
                }
                .reduce { acc, curr -> acc.intersect(curr) }
                .size
        }
        .sum()
        .also(::println)
}
