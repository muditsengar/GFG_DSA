package com.ms.dsa_questions._1_introduction

val TAG = "INTRODUCTION"

fun main() {
//    println(sumOfNNumbers(10))

    giveComplexity(10)
}

fun sumOfNNumbers(n: Int): Int {
    return n.times(n.inc()).div(2)
}

fun giveComplexity(n: Int) {
    var count = 0
    for (i in 0 until n) {
        for (j in i downTo 0) {
            count += 1
            println("giveComplexity: i : $i  |  j : $j   |  count : $count")
        }
        println()
    }
//    Log.e(TAG, "giveComplexity: count - "+count )
}