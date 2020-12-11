@file:JvmName("Part1")
package com.jonesetc.aoc2020.day11

import com.jonesetc.aoc2020.inputFile

enum class TileState {
    FLOOR, EMPTY, OCCUPIED
}

typealias FloorState = List<List<TileState>>

val vectors = listOf(
    Pair(-1, -1),
    Pair(0, -1),
    Pair(1, -1),
    Pair(-1, 0),
    Pair(1, 0),
    Pair(-1, 1),
    Pair(0, 1),
    Pair(1, 1)
)

fun FloorState.countOccupied(): Int {
    return this.map { row ->
        row.filter { tile ->
            tile == TileState.OCCUPIED
        }.count()
    }.sum()
}

private fun FloorState.countNeighbors(x: Int, y: Int): Int {
    return vectors
        .map { (deltaX, deltaY) -> Pair(x + deltaX, y + deltaY) }
        .filter { (neighborX, neighborY) ->
            this.getOrNull(neighborX)?.getOrNull(neighborY) == TileState.OCCUPIED
        }
        .count()
}

private fun FloorState.simulateRound(): FloorState {
    return this.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, tile ->
            val neighbors = countNeighbors(rowIndex, columnIndex)
            when {
                tile == TileState.EMPTY && neighbors == 0 -> TileState.OCCUPIED
                tile == TileState.OCCUPIED && neighbors >= 4 -> TileState.EMPTY
                else -> tile
            }
        }
    }
}

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val floorState = inputFile(11)
        .readLines()
        .map { line ->
            line.map { tile ->
                when (tile) {
                    'L' -> TileState.EMPTY
                    else -> TileState.FLOOR
                }
            }
        }

    generateSequence(floorState, { it.simulateRound() })
        .windowed(2)
        .filter { (prev, curr) -> prev == curr }
        .take(1)
        .map { (_, curr) -> curr.countOccupied() }
        .forEach(::println)
}
