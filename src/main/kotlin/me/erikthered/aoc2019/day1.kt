package me.erikthered.aoc2019

import java.io.File

fun main() {
    val lines = File("src/main/resources/input-day1.txt").readLines()

    val totalFuelPt1 = lines.map { Integer.parseInt(it) }
            .map { it / 3 - 2 }
            .reduce { acc, fuel -> acc + fuel }

    println(totalFuelPt1)

    val totalFuelPt2 = lines.map { Integer.parseInt(it) }
            .fold(0) { acc, load -> acc + calcFuel(load)}

    println(totalFuelPt2)
}

fun calcFuel(load: Int): Int {
    var fuel = 0
    var chunk = load
    do {
        fuel += chunk / 3 - 2
        chunk = chunk / 3 - 2
    } while (chunk /3 -2 > 0)
    return fuel
}
