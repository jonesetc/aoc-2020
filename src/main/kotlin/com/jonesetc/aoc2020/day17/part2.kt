@file:JvmName("Part2")
package com.jonesetc.aoc2020.day17

import com.jonesetc.aoc2020.inputFile
import kotlin.math.max

private val vectors = (-1..1)
    .flatMap { x ->
        (-1..1)
            .flatMap { y ->
                (-1..1)
                    .flatMap { z ->
                        (-1..1)
                            .map { w ->
                                Coordinate(x, y, z, w)
                            }
                    }
            }
    }
    .filter { it != Coordinate() }

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    var cube = inputFile(17)
        .readLines()
        .flatMapIndexed { x, row ->
            row
                .mapIndexed { y, tile ->
                    Pair(Coordinate(x, y), tile == '#')
                }
        }
        .toMap()

    val (minX, minY, minZ, minW) = Coordinate()
    val (maxX, maxY, maxZ, maxW) = cube.keys.fold(
        Coordinate(),
        { acc, coord ->
            acc.copy(
                x = max(acc.x, coord.x),
                y = max(acc.y, coord.y),
                z = max(acc.z, coord.z),
                w = max(acc.w, coord.w)
            )
        }
    )

    for (cycle in 1..6) {
        ((minX - cycle)..(maxX + cycle))
            .flatMap { x ->
                ((minY - cycle)..(maxY + cycle))
                    .flatMap { y ->
                        ((minZ - cycle)..(maxZ + cycle))
                            .flatMap { z ->
                                ((minW - cycle)..(maxW + cycle))
                                    .map { w ->
                                        val coord = Coordinate(x, y, z, w)
                                        val isActive = cube[coord] ?: false
                                        val activeNeighbors = vectors.map { offset ->
                                            cube[coord + offset] ?: false
                                        }.filter { it }.count()

                                        when {
                                            isActive and (activeNeighbors !in 2..3) -> false
                                            !isActive and (activeNeighbors == 3) -> true
                                            else -> isActive
                                        }.let { Pair(coord, it) }
                                    }
                            }
                    }
            }
            .toMap().also { cube = it }
    }

    cube.values.filter { it }.count().also(::println)
}
