@file:JvmName("Part2")
package com.jonesetc.aoc2020.day3

import com.jonesetc.aoc2020.inputFile

private const val treeChar = '#'

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val lines = inputFile(3).readLines()
    val width = lines.first().length

    val overOne = lines.withIndex().filter { (i, line) ->
        line[i % width] == treeChar
    }.size.toLong()
    val overThree = lines.withIndex().filter { (i, line) ->
        line[(i * 3) % width] == treeChar
    }.size.toLong()
    val overFive = lines.withIndex().filter { (i, line) ->
        line[(i * 5) % width] == treeChar
    }.size.toLong()
    val overSeven = lines.withIndex().filter { (i, line) ->
        line[(i * 7) % width] == treeChar
    }.size.toLong()
    val overHalf = lines.withIndex().filter { (i, _) ->
        i % 2 == 0
    }.filter { (i, line) ->
        line[(i / 2) % width] == treeChar
    }.size.toLong()

    println(overOne * overThree * overFive * overSeven * overHalf)
}
