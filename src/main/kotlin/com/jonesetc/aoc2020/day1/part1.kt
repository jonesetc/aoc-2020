@file:JvmName("Part1")
package com.jonesetc.aoc2020.day1

import com.jonesetc.aoc2020.inputFile

private const val target: Int = 2020

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val seen: MutableSet<Int> = mutableSetOf()

    inputFile(1).forEachLine {
        val parsed: Int = it.toInt()
        val compliment: Int = target - parsed

        if (seen.contains(compliment)) {
            println(compliment * parsed)
        } else {
            seen.add(parsed)
        }
    }
}
