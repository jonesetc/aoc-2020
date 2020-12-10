@file:JvmName("Part1")
package com.jonesetc.aoc2020.day9

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(9)
        .readLines()
        .map(String::toLong)
        .windowed(26)
        .map { Pair(it.dropLast(1).toSet(), it.last()) }
        .dropWhile { (available, current) ->
            available
                .any { old ->
                    val compliment = current - old
                    compliment != old && compliment in available
                }
        }
        .take(1)
        .map { it.second }
        .forEach(::println)
}
