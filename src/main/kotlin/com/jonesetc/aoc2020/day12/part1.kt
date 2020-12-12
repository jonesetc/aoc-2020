@file:JvmName("Part1")
package com.jonesetc.aoc2020.day12

import com.jonesetc.aoc2020.inputFile
import kotlin.math.absoluteValue

enum class Heading(val vector: Pair<Int, Int>) {
    N(Pair(0, -1)),
    E(Pair(1, 0)),
    S(Pair(0, 1)),
    W(Pair(-1, 0))
}

enum class Direction(val degrees: Int) {
    L(-90),
    R(90)
}

data class ShipState(
    val direction: Heading = Heading.E,
    val waypoint: Pair<Int, Int> = Pair(10, -1),
    val position: Pair<Int, Int> = Pair(0, 0)
)

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(12)
        .readLines()
        .fold(
            ShipState(),
            { state, line ->
                val action = line.substring(0, 1)
                val value = line.substring(1).toInt()
                when (action) {
                    "F" -> {
                        state.copy(
                            position = Pair(
                                state.position.first + (state.direction.vector.first * value),
                                state.position.second + (state.direction.vector.second * value)
                            )
                        )
                    }
                    "L", "R" -> {
                        val direction = Direction.valueOf(action)
                        state.copy(
                            direction = Heading.values()[
                                Math.floorMod(state.direction.ordinal + (value / direction.degrees), Heading.values().size)
                            ]
                        )
                    }
                    "N", "E", "W", "S" -> {
                        val heading = Heading.valueOf(action)
                        state.copy(
                            position = Pair(
                                state.position.first + (heading.vector.first * value),
                                state.position.second + (heading.vector.second * value)
                            )
                        )
                    }
                    else -> state
                }
            }
        )
        .position
        .also { println("${it.first.absoluteValue + it.second.absoluteValue}") }
}
