package me.erikthered.aoc2019

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val lines = File("src/main/resources/input-day3.txt").readLines()

    val wire1 = parsePaths(lines[0])
    val wire2 = parsePaths(lines[1])

    val intersections = wire1.intersect(wire2).filter { it != 0 to 0 }

    println("Shortest distance:")
    println(intersections.map { it.first.absoluteValue + it.second.absoluteValue }.min())

    println("Intersections:")
    intersections.forEach {
        println("$it ${wire1.indexOf(it)} ${wire2.indexOf(it)} ${wire1.indexOf(it) + wire2.indexOf(it) + 2}")
    }

    println("Test case:")
    val test1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
    val test2 = "U62,R66,U55,R34,D71,R55,D58,R83"

    val testWire1 = parsePaths(test1)
    val testWire2 = parsePaths(test2)

    val testIntersections = testWire1.intersect(testWire2).filter { it != 0 to 0 }

    println(testIntersections.map { it.first.absoluteValue + it.second.absoluteValue }.min())

    testIntersections.forEach {
        // Off by 2 since each array is 0 indexed
        println("$it ${testWire1.indexOf(it)} ${testWire2.indexOf(it)} ${testWire1.indexOf(it) + testWire2.indexOf(it) + 2}")
    }
}

typealias Point = Pair<Int, Int>

typealias Path = Pair<Direction, Int>

fun parsePaths(input: String): List<Point> {
    return input.split(",")
            .map { Direction.valueOf(it[0].toString()) to it.substring(1).toInt() }
            .fold(listOf<Point>()) { pts, path -> pts + (generatePoints(if (pts.isEmpty()) 0 to 0 else pts.last(), path)) }
}

fun generatePoints(start: Point, path: Path): List<Point> {
    return when (path.first) {
        Direction.L -> (start.first - 1 downTo start.first - path.second).map { it to start.second }
        Direction.R -> (start.first + 1 .. start.first + path.second).map { it to start.second }
        Direction.U -> (start.second + 1 .. start.second + path.second).map { start.first to it }
        Direction.D -> (start.second - 1 downTo start.second - path.second).map { start.first to it }
    }
}

enum class Direction {
    L, R, U, D
}