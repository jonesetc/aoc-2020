@file:JvmName("Part1")
package com.jonesetc.aoc2020.day7

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val colorParents =
        inputFile(7)
            .readLines()
            .map { rawLine ->
                rawLine
                    .split(" contain ", ", ")
                    .map { rawPart ->
                        rawPart
                            .removeSuffix(".")
                            .removeSuffix("s")
                            .removeSuffix(" bag")
                            .split(" ")
                            .let { parts ->
                                when (parts.size) {
                                    3 -> parts.drop(1)
                                    else -> parts
                                }.joinToString(" ")
                            }
                    }
                    .filter { color -> color != "no other" }
            }
            .flatMap { parts ->
                parts
                    .drop(1)
                    .map { color -> Pair(color, parts[0]) }
            }
            .groupBy(Pair<String, String>::first, Pair<String, String>::second)

    val seenColors: MutableSet<String> = mutableSetOf()
    var currentColors = colorParents["shiny gold"]!!

    while (currentColors.isNotEmpty()) {
        seenColors.addAll(currentColors)
        currentColors = currentColors
            .flatMap { color ->
                colorParents[color]?.filter { parentColor ->
                    parentColor !in seenColors
                } ?: listOf()
            }
    }

    println(seenColors.size)
}
