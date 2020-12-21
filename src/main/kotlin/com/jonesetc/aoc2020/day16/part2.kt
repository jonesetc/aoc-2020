@file:JvmName("Part2")
package com.jonesetc.aoc2020.day16

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val parts = inputFile(16)
        .readText()
        .trim()
        .split("\n\n")

    val ruleMap = parseRules(parts[0])
    val myTicket = parts[1]
        .split("\n")
        .last()
        .let(::parseTicket)
    val otherTickets = parts[2]
        .split("\n")
        .drop(1)
        .map(::parseTicket)
        .filter { ticket ->
            ticket
                .all { value ->
                    ruleMap
                        .values
                        .any { ranges ->
                            ranges
                                .any { range ->
                                    value in range
                                }
                        }
                }
        }

    val columnLabels = List(myTicket.size) { ruleMap.keys.toMutableList() }

    otherTickets
        .forEach { ticket ->
            ticket.forEachIndexed { i, value ->
                ruleMap
                    .forEach { (field, ranges) ->
                        if (ranges.none { value in it }) {
                            columnLabels[i].remove(field)
                        }
                    }
            }
        }

    while (columnLabels.map(Collection<*>::size).any { it != 1 }) {
        val grouped = columnLabels
            .groupBy { it.size == 1 }

        grouped[false]!!.forEach { it.removeAll(grouped[true]!!.flatten()) }
    }

    columnLabels
        .flatMapIndexed { i, (label) ->
            when {
                label.startsWith("departure") -> listOf(myTicket[i])
                else -> listOf()
            }
        }
        .map(Int::toLong)
        .reduce(Long::times)
        .also(::println)
}
