@file:JvmName("Part2")
package com.jonesetc.aoc2020.day4

import com.jonesetc.aoc2020.inputFile

private const val minFields = 7

fun main(@Suppress("UNUSED_PARAMETER") args: Array<String>) {
    inputFile(4)
        .readText()
        .split("\n\n")
        .map { rawPassport -> rawPassport.split(" ", "\n").filter(::validField).size }
        .filter { numFields -> numFields >= minFields }
        .size
        .also(::println)
}

fun validByr(rawValue: String): Boolean = rawValue.toInt() in 1920..2002

fun validIyr(rawValue: String): Boolean = rawValue.toInt() in 2010..2020

fun validEyr(rawValue: String): Boolean = rawValue.toInt() in 2020..2030

fun validHgt(rawValue: String): Boolean {
    return when {
        rawValue.endsWith("cm") -> rawValue.substring(0 until rawValue.length - 2).toInt() in 150..193
        rawValue.endsWith("in") -> rawValue.substring(0 until rawValue.length - 2).toInt() in 59..76
        else -> false
    }
}

fun validHcl(rawValue: String): Boolean {
    return rawValue.startsWith("#") &&
        rawValue.length == 7 &&
        rawValue.substring(1 until rawValue.length).all { hexDigit -> hexDigit in "0123456789abcdef" }
}

fun validEcl(rawValue: String): Boolean = rawValue in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

fun validPid(rawValue: String): Boolean = rawValue.length == 9 && rawValue.all { digit -> digit in "0123456789" }

fun validField(rawField: String): Boolean {
    return rawField.split(":").let { parts ->
        when {
            parts[0] == "byr" -> validByr(parts[1])
            parts[0] == "iyr" -> validIyr(parts[1])
            parts[0] == "eyr" -> validEyr(parts[1])
            parts[0] == "hgt" -> validHgt(parts[1])
            parts[0] == "hcl" -> validHcl(parts[1])
            parts[0] == "ecl" -> validEcl(parts[1])
            parts[0] == "pid" -> validPid(parts[1])
            else -> false
        }
    }
}
