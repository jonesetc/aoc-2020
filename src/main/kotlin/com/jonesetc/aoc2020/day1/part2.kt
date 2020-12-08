@file:JvmName("Part2")
package com.jonesetc.aoc2020.day1

import com.jonesetc.aoc2020.inputFile

private const val target: Int = 2020

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val seen: MutableSet<Int> = mutableSetOf()
    val smallPairs: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    inputFile(1).forEachLine { raw ->
        val parsed: Int = raw.toInt()
        val compliment: Int = target - parsed

        smallPairs[compliment]?.also { pair ->
            println(pair.first * pair.second * parsed)
        }

        seen.add(parsed)
        for (previous in seen) {
            if (previous + parsed < target) {
                smallPairs[previous + parsed] = Pair(previous, parsed)
            }
        }
    }
}
