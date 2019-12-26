package me.erikthered.aoc2019

import java.io.File

fun main() {
    val input = File("src/main/resources/input-day2.txt").readText().split(",").map { it.toInt() }

    // Changes per part 1 instructions
    val part1Input = input.toMutableList()
    part1Input[1] = 12
    part1Input[2] = 2

    println(executeCommands(part1Input))

    // Part 2
    for (noun in 0..99){
        for (verb in 0..99) {
            val part2Input = input.toMutableList()
            part2Input[1] = noun
            part2Input[2] = verb
            if (executeCommands(part2Input) == 19690720) {
                println(100 * noun + verb)
                break
            }
        }
    }
}

private fun executeCommands(cmds: MutableList<Int>): Int {
    var cursor = 0

    do {
        val dest = cmds[cursor + 3]
        val pos1 = cmds[cursor + 1]
        val pos2 = cmds[cursor + 2]
        when (cmds[cursor]) {
            1 -> cmds[dest] = (cmds[pos1] + cmds[pos2])
            2 -> cmds[dest] = (cmds[pos1] * cmds[pos2])
        }
        cursor += 4
    } while (cmds[cursor] != 99)

    return cmds[0]
}