@file:JvmName("Part1")
package com.jonesetc.aoc2020.day8

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val bootCode = inputFile(8)
        .readLines()
        .map { line ->
            line
                .split(" ")
                .let { parts -> Pair(parts[0], parts[1].toInt()) }
        }

    var instructionPointer = 0
    var accumulator = 0
    val seenInstructions = mutableSetOf<Int>()

    while (instructionPointer !in seenInstructions) {
        seenInstructions.add(instructionPointer)
        val instruction = bootCode[instructionPointer]

        when (instruction.first) {
            "nop" -> instructionPointer += 1
            "jmp" -> instructionPointer += instruction.second
            "acc" -> {
                accumulator += instruction.second
                instructionPointer += 1
            }
        }
    }

    println(accumulator)
}
