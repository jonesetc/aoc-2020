@file:JvmName("Part2")
package com.jonesetc.aoc2020.day14

import com.jonesetc.aoc2020.inputFile

fun Long.possibilities(possibleFlips: Set<Long>): Set<Long> {
    if (possibleFlips.isEmpty()) {
        return setOf(this)
    }

    val possibleFlip = possibleFlips.first()
    val remainingFlips = possibleFlips.drop(1).toSet()

    return setOf(
        this.possibilities(remainingFlips),
        (this xor possibleFlip).possibilities(remainingFlips)
    ).flatten().toSet()
}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(14)
        .readLines()
        .map { line -> line.split(" = ") }
        .fold(
            MemoryState(),
            { acc, (op, value) ->
                when (op) {
                    "mask" -> {
                        acc.possibleFlips.clear()
                        acc.oneMask = 0L

                        value
                            .reversed()
                            .forEachIndexed { index, bit ->
                                when (bit) {
                                    'X' -> acc.possibleFlips.add(1L shl index)
                                    '1' -> acc.oneMask = acc.oneMask or (1L shl index)
                                }
                            }

                        acc
                    }
                    else -> {
                        op
                            .removePrefix("mem[")
                            .removeSuffix("]")
                            .toLong()
                            .let { it or acc.oneMask }
                            .possibilities(acc.possibleFlips)
                            .forEach { acc.memory[it] = value.toLong() }

                        acc
                    }
                }
            }
        )
        .memory.values.sum().also(::println)
}
