@file:JvmName("Part1")
package com.jonesetc.aoc2020.day14

import com.jonesetc.aoc2020.inputFile

data class MemoryState(
    var zeroMask: Long = 0L,
    var oneMask: Long = 0L,
    var possibleFlips: MutableSet<Long> = mutableSetOf(),
    val memory: MutableMap<Long, Long> = mutableMapOf()
)

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(14)
        .readLines()
        .map { line -> line.split(" = ") }
        .fold(
            MemoryState(),
            { acc, (op, value) ->
                when (op) {
                    "mask" -> {
                        acc.zeroMask = 0L
                        acc.oneMask = 0L

                        value
                            .reversed()
                            .forEachIndexed { index, bit ->
                                when (bit) {
                                    '0' -> acc.zeroMask = acc.zeroMask or (1L shl index)
                                    '1' -> acc.oneMask = acc.oneMask or (1L shl index)
                                }
                            }

                        acc
                    }
                    else -> {
                        acc.memory[
                            op
                                .removePrefix("mem[")
                                .removeSuffix("]")
                                .toLong()
                        ] = value
                            .toLong()
                            .let { it and acc.zeroMask.inv() }
                            .let { it or acc.oneMask }

                        acc
                    }
                }
            }
        )
        .memory.values.sum().also(::println)
}
