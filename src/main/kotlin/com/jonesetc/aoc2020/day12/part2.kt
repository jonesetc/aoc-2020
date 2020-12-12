@file:JvmName("Part2")
package com.jonesetc.aoc2020.day12

import com.jonesetc.aoc2020.inputFile
import kotlin.math.absoluteValue

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
                                state.position.first + (state.waypoint.first * value),
                                state.position.second + (state.waypoint.second * value)
                            )
                        )
                    }
                    "L", "R" -> {
                        when (Math.floorMod(value / Direction.valueOf(action).degrees, 4)) {
                            1 -> {
                                state.copy(
                                    waypoint = Pair(
                                        state.waypoint.second * -1,
                                        state.waypoint.first
                                    )
                                )
                            }
                            2 -> {
                                state.copy(
                                    waypoint = Pair(
                                        state.waypoint.first * -1,
                                        state.waypoint.second * -1
                                    )
                                )
                            }
                            3 -> {
                                state.copy(
                                    waypoint = Pair(
                                        state.waypoint.second,
                                        state.waypoint.first * -1
                                    )
                                )
                            }
                            else -> state
                        }
                    }
                    "N", "E", "W", "S" -> {
                        val heading = Heading.valueOf(action)
                        state.copy(
                            waypoint = Pair(
                                state.waypoint.first + (heading.vector.first * value),
                                state.waypoint.second + (heading.vector.second * value)
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
