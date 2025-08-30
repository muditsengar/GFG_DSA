package com.ms.gfg

fun main() {

}

fun findAllSubArrays(ar: IntArray): Int {
    var result = 0

    for (i in 0 until ar.size) {
        for (j in i until ar.size) {
            for (k in i..j) {
                result++
            }
        }
    }

    return result
}

//fun findLargestElementRecursively(ar: IntArray): Int {
//    var currentIndex = 0
//    if (ar.size == 1) {
//        return ar[currentIndex]
//    }
////    if (ar[++currentIndex])
//}

fun findFactorialRecursively(n: Int): Int {
    var result = 1

    if (n == 1) return result
    return findFactorialRecursively(n - 1)
}

fun findLeaders(ar: IntArray): ArrayList<Int> {
    var resultAr = arrayListOf<Int>()

    return resultAr
}

fun findSecondLargestElementRecursively(ar: IntArray): Int {
    var result = -1
    var largest = -1

    val arSize = ar.size

    if (arSize == 1) {
        result = ar[0]
    }
    for (i in 0 until arSize / 2) {
        if (ar[i] > largest) {
            largest = ar[i]
        } else if (ar[i] in (result + 1) until largest) {
            result = ar[i]
        }

        val j = arSize - i - 1
        if (ar[j] > largest) {
            largest = ar[j]
        } else if (ar[j] in (result + 1) until largest) {
            result = ar[j]
        }
    }

    if (result == -1) {
        result = largest
    }

    return result
}