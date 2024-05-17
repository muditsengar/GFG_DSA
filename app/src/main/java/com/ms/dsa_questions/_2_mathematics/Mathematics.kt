package com.ms.dsa_questions._2_mathematics

fun main() {
//    println(calculateNumberOfDigitsRecursively(1234598765876435467L))

//    println(factorialRecursively(8))

//    println(nthFibonacciNumber(9))

    println(checkPalindrome("qwerewqq"))

}

fun countTrailingZeroesInAFactorial(n: Int){

}

fun checkPalindrome(s: String): Boolean {
    val charArray = s.toCharArray()
    for (i in 0 until s.length / 2) {
        if (charArray[i] != charArray[s.length - 1 - i])
            return false

    }
    return true
}

fun nthFibonacciNumber(n: Int): Int {
    if (n <= 1) return n
    return nthFibonacciNumber(n - 1) + nthFibonacciNumber(n - 2)
}

fun factorialRecursively(n: Int): Int {
    if (n == 1) return 1
    return n * factorialRecursively(n - 1)
}

fun calculateNumberOfDigitsRecursively(n: Long): Int {
    if (n == 0L) return 0
    return 1 + calculateNumberOfDigitsRecursively(n / 10)
//    println("count - $count")
}
