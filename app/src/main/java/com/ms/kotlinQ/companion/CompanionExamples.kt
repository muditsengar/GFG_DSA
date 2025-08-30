package com.ms.kotlinQ.companion

import java.text.DecimalFormat

class CompanionExamples {

}

object GlobalHelper {
    fun method() = "Global Helper Method"
}

class MyClass {
    companion object {
        fun method1() = "Companion Object Method"
    }


    object Helper1 {
        fun method2() = "Helper1 Object Method"
    }

    object Helper2 {
        fun method3() = "Helper2 Object Method"
    }
}

fun main() {
//    println(MyClass.method1())       // Access companion object
//    println(MyClass.Helper1.method2()) // Access Helper1 object
//    println(MyClass.Helper2.method3()) // Access Helper2 object

//    calcSeries()

    flatMapExample()
}

data class Order(
    val lines: List<OrderLine>
)

data class OrderLine(
    val name: String,
    val price: Int
)

val orders = listOf(
    Order(listOf(OrderLine("Garlic", 1), OrderLine("Chives", 2))),
    Order(listOf(OrderLine("Tomato", 3), OrderLine("Garlic", 4))),
    Order(listOf(OrderLine("Potato", 5), OrderLine("Chives", 6))),
)

fun flatMapExample() {

    val orderLinesList: List<List<OrderLine>> = orders.map { it.lines }
    val orderLinesList1: ArrayList<OrderLine> = arrayListOf()

    orders.forEach {
        orderLinesList1.addAll(it.lines)
    }

    val lines: List<OrderLine> = orders.flatMap { it.lines }


    val list = listOf(1, 2, 3, 4, 5)
    val result: List<Int> = list.flatMap { it: Int ->
        listOf(it, it * 10)
    }

    val mapResult = list.map { it: Int ->
        it * 10
    }
    println(result)
    println(mapResult)

}


fun calcSeries() {
//    val start: Double = 1500000.0
////    val start: Double = 4_00_00_000.0
//    val increaseRate: Double = 30.0 / 100  // 1%
//    val count = 30
//
//    var current = start
//    var sum: Double = 0.0
//
//    println("| No.          | Number                 |      Difference   |")
//    println("|----          |------------           |-------------          |")
//
//    for (i in 1..count) {
//        val difference = if (i == 1) 0.0 else current - (current / (1 + increaseRate))
//
//        println("| $i  | $current | $difference |")
//
//        sum += current
//        current *= 1 + increaseRate
//    }
//
//    println("\nSum of the Number Column: $sum")


    val start = 1500000.0
    val increaseRate = 30.0 / 100  // 1%
    val count = 30

    val decimalFormat = DecimalFormat("#,###") // Format numbers with commas

    var current = start
    var sum = 0.0

    println("| No. | Number         | Difference    |")
    println("|----|---------------|--------------|")

    for (i in 1..count) {
        val difference = if (i == 1) 0.0 else current - (current / (1 + increaseRate))

        println(
            "| ${String.format("%3d", i)} | ${String.format("%12s", decimalFormat.format(current))} | ${
                String.format(
                    "%12s",
                    decimalFormat.format(difference)
                )
            } |"
        )

        sum += current
        current *= 1 + increaseRate
    }

    println("\nSum of the Number Column: ${decimalFormat.format(sum)}")
}
