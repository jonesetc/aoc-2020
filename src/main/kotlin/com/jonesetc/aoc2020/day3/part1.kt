@file:JvmName("Part1")
package com.jonesetc.aoc2020.day3

import com.jonesetc.aoc2020.inputFile

private const val treeChar = '#'

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val lines = inputFile(3).readLines()
    val width = lines.first().length

    lines.withIndex().filter { (i, line) ->
        line[(i * 3) % width] == treeChar
    }.size.also(::println)
}
