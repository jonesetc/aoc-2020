@file:JvmName("Part2")
package com.jonesetc.aoc2020.day15

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val turnMap = inputFile(15)
        .readLines()
        .first()
        .split(",")
        .map(String::toInt)
        .mapIndexed { i, value -> Pair(value, mutableListOf(i + 1)) }
        .toMap()
        .toMutableMap()

    var lastSpoken = turnMap
        .entries.maxByOrNull { it.value.first() }!!
        .key

    for (turn in (turnMap.size + 1)..30000000) {
        val spokenAt = turnMap[lastSpoken]!!

        lastSpoken = if (spokenAt.size < 2) {
            0
        } else {
            spokenAt.takeLast(2).let { it.last() - it.first() }
        }

        turnMap.computeIfAbsent(lastSpoken) { mutableListOf() }
        turnMap[lastSpoken]!!.add(turn)
    }

    println(lastSpoken)
}
