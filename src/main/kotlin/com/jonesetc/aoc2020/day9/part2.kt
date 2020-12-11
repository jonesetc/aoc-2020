@file:JvmName("Part2")
package com.jonesetc.aoc2020.day9

import com.jonesetc.aoc2020.inputFile

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    val numbers = inputFile(9)
        .readLines()
        .map(String::toLong)

    val target = numbers
        .windowed(26)
        .map { Pair(it.dropLast(1).toSet(), it.last()) }
        .dropWhile { (available, current) ->
            available
                .any { old ->
                    val compliment = current - old
                    compliment != old && compliment in available
                }
        }
        .take(1)
        .map { it.second }
        .first()

    numbers.indices
        .flatMap { start ->
            numbers
                .drop(start)
                .scan(
                    listOf<Long>(),
                    { acc, curr ->
                        acc.plus(curr)
                    }
                )
                .dropWhile { subList -> subList.sum() < target }
                .take(1)
                .filter { subList -> subList.sum() == target }
                .map { sublist -> sublist.sorted() }
                .map { sortedSubList -> sortedSubList.first() + sortedSubList.last() }
        }
        .take(1)
        .forEach(::println)
}
