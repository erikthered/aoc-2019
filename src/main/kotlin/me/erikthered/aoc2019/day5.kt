package me.erikthered.aoc2019

import java.io.File

fun main() {
    val oplist = File("src/main/resources/input-day5.txt").readText().split(",").map { it.toInt() }

    val cmds = oplist.toMutableList()

    var done = false
    var pointer = 0

    println("Enter input value:")
    val input = readLine()?.toInt() ?: throw RuntimeException("Bad input")

    do {
        val inst = cmds[pointer]
        val padded = inst.toString().padStart(5, '0')

        // determine opcode
        val opcode = padded.substring(padded.length - 2)
        // reverse mode settings to match natural parameter order
        val modes = padded.substring(0, padded.length - 2).reversed()

        // determine number of commands and modes of commands
        when (opcode) {
            "01" -> {
                val operand1 = if(modes[0] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand2 = if(modes[1] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand3 = cmds[++pointer]
                cmds[operand3] = operand1 + operand2
                pointer++
            }
            "02" -> {
                val operand1 = if(modes[0] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand2 = if(modes[1] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand3 = cmds[++pointer]
                cmds[operand3] = operand1 * operand2
                pointer++
            }
            "03" -> {
                val operand1 = cmds[++pointer]
                cmds[operand1] = input
                pointer++
            }
            "04" -> {
                val operand1 = if(modes[0] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                println(operand1)
                pointer++
            }
            "05" -> {
                val operand1 = if(modes[0] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand2 = if(modes[1] == '0') cmds[cmds[++pointer]] else cmds[++pointer]

                if (operand1 != 0) {
                    pointer = operand2
                } else {
                    pointer++
                }
            }
            "06" -> {
                val operand1 = if(modes[0] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand2 = if(modes[1] == '0') cmds[cmds[++pointer]] else cmds[++pointer]

                if (operand1 == 0) {
                    pointer = operand2
                } else {
                    pointer++
                }
            }
            "07" -> {
                val operand1 = if(modes[0] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand2 = if(modes[1] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand3 = cmds[++pointer]

                cmds[operand3] = if (operand1 < operand2) 1 else 0

                pointer++
            }
            "08" -> {
                val operand1 = if(modes[0] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand2 = if(modes[1] == '0') cmds[cmds[++pointer]] else cmds[++pointer]
                val operand3 = cmds[++pointer]

                cmds[operand3] = if (operand1 == operand2) 1 else 0

                pointer++
            }
            "99" -> done = true
            else -> throw RuntimeException("Invalid opcode $opcode")
        }

        println("Instruction pointer: $pointer")

    } while (!done)
}