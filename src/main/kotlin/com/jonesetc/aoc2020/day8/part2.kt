@file:JvmName("Part2")
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

    for (i in bootCode.indices) {
        val swappedOp = when (bootCode[i].first) {
            "nop" -> "jmp"
            "jmp" -> "nop"
            else -> continue
        }

        while (instructionPointer !in seenInstructions && instructionPointer != bootCode.size) {
            seenInstructions.add(instructionPointer)
            val instruction = bootCode[instructionPointer]
                .let {
                    when (instructionPointer) {
                        i -> it.copy(first = swappedOp)
                        else -> it
                    }
                }

            when (instruction.first) {
                "nop" -> instructionPointer += 1
                "jmp" -> instructionPointer += instruction.second
                "acc" -> {
                    accumulator += instruction.second
                    instructionPointer += 1
                }
            }
        }

        if (instructionPointer == bootCode.size) {
            println(accumulator)
            break
        }

        instructionPointer = 0
        accumulator = 0
        seenInstructions.clear()
    }
}
