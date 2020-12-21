@file:JvmName("Part1")
package com.jonesetc.aoc2020.day17

import com.jonesetc.aoc2020.inputFile
import kotlin.math.max

data class Coordinate(
    val x: Int = 0,
    val y: Int = 0,
    val z: Int = 0,
    val w: Int = 0,
)
operator fun Coordinate.plus(other: Coordinate): Coordinate {
    return this.copy(
        x = this.x + other.x,
        y = this.y + other.y,
        z = this.z + other.z,
        w = this.w + other.w
    )
}

private val vectors = (-1..1)
    .flatMap { x ->
        (-1..1)
            .flatMap { y ->
                (-1..1)
                    .map { z ->
                        Coordinate(x, y, z,)
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

    val (minX, minY, minZ) = Coordinate()
    val (maxX, maxY, maxZ) = cube.keys.fold(
        Coordinate(),
        { acc, coord ->
            acc.copy(
                x = max(acc.x, coord.x),
                y = max(acc.y, coord.y),
                z = max(acc.z, coord.z)
            )
        }
    )

    for (cycle in 1..6) {
        ((minX - cycle)..(maxX + cycle))
            .flatMap { x ->
                ((minY - cycle)..(maxY + cycle))
                    .flatMap { y ->
                        ((minZ - cycle)..(maxZ + cycle))
                            .map { z ->
                                val coord = Coordinate(x, y, z)
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
            .toMap().also { cube = it }
    }

    cube.values.filter { it }.count().also(::println)
}
