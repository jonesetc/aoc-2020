@file:JvmName("Part1")
package com.jonesetc.aoc2020.day4

import com.jonesetc.aoc2020.inputFile

private const val minFields = 7
private val optionalFields = setOf("cid")

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(4)
        .readText()
        .split("\n\n")
        .map { rawPassport ->
            rawPassport.split(" ", "\n").map { rawField ->
                rawField.split(":").first()
            }.filter { key -> key !in optionalFields }.size
        }
        .filter { numFields -> numFields >= minFields }
        .size
        .also(::println)
}
