package me.erikthered.aoc2019

fun main() {
    val input = 367479 .. 893698
    println(isValidPt1(111111.toString()))
    println(isValidPt1(223450.toString()))
    println(isValidPt1(123789.toString()))

    val part1Candidates = input.filter { isValidPt1(it.toString())}
    println(part1Candidates.size)

    println(isValidPt2(111111.toString()))
    println(isValidPt2(123444.toString()))
    println(isValidPt2(111122.toString()))

    val part2Candidates = part1Candidates.filter { isValidPt2(it.toString())}
    println(part2Candidates.size)
}

fun isValidPt1(value: String): Boolean {
    var prevDigit = 0
    var twoSameAdj = false

    for(c in value) {
        val digit = c.toInt()
        if(!twoSameAdj && digit == prevDigit) {
            twoSameAdj = true
        }

        if (digit < prevDigit)
            return false

        prevDigit = digit
    }

    return twoSameAdj
}

fun isValidPt2(value: String): Boolean {
    val digits = value.map { it.toInt() }
    var idx = 0
    var candidate = false

    do {
        if (digits[idx] == digits[idx+1]){
            val lookBehindMatch = idx > 0 && digits[idx] == digits[idx-1]
            val lookAheadMatch = idx < digits.size - 2 && digits[idx] == digits[idx+2]
            candidate = !lookBehindMatch && !lookAheadMatch
        }
        idx++
    } while (idx < digits.size - 1 && !candidate)

    return candidate
}