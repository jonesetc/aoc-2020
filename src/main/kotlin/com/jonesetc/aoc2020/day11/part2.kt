@file:JvmName("Part2")
package com.jonesetc.aoc2020.day11

import com.jonesetc.aoc2020.inputFile

private fun FloorState.countNeighbors(x: Int, y: Int): Int {
    return generateSequence(
        { vectors.map { (deltaX, deltaY) -> Pair(x + deltaX, y + deltaY) } },
        { previous ->
            previous.mapIndexed { index, (neighborX, neighborY) ->
                when (this.getOrNull(neighborX)?.getOrNull(neighborY)) {
                    TileState.FLOOR -> Pair(neighborX + vectors[index].first, neighborY + vectors[index].second)
                    else -> Pair(neighborX, neighborY)
                }
            }
        }
    )
        .windowed(2)
        .filter { (prev, curr) -> prev == curr }
        .take(1)
        .map { (_, curr) ->
            curr.filter { (neighborX, neighborY) ->
                this.getOrNull(neighborX)?.getOrNull(neighborY) == TileState.OCCUPIED
            }.count()
        }
        .sum()
}

private fun FloorState.simulateRound(): FloorState {
    return this.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, tile ->
            val neighbors = countNeighbors(rowIndex, columnIndex)
            when {
                tile == TileState.EMPTY && neighbors == 0 -> TileState.OCCUPIED
                tile == TileState.OCCUPIED && neighbors >= 5 -> TileState.EMPTY
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
