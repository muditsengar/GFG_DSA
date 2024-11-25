package com.ms.leetcode.array

import kotlin.math.min

fun getConcatenation(nums: IntArray): IntArray {
    val result: IntArray = IntArray(nums.size * 2)
    for (i in nums.indices) {
        result[i] = nums[i]
        result[i + nums.size] = nums[i]
    }
    return result
}

fun buildArray(nums: IntArray): IntArray {
    val result = IntArray(nums.size)
    for (i in 0 until nums.size) {
        result[i] = nums[nums[i]]
    }
    return result
}

fun minimumOperations(nums: IntArray): Int {
    var result = 0
    for (i in nums.indices) {
        result += min(nums[i] % 3, 3 - (nums[i] % 3))
    }

    return result
}

fun finalValueAfterOperations(operations: Array<String>): Int {
    var result = 0
    for (i in operations.indices) {
        when {
            operations[i].contains("++") -> {
                result++
            }

            else -> {
                result--
            }
        }
    }
    return result
}

fun maxArea(heightAr: IntArray): Int {
    var result = 0
    var length = 0
    var width = 0

    for (i in heightAr.indices) {
        for (j in (i + 1) until heightAr.size) {
            val area = min(heightAr[i], heightAr[j]) * (j - i)
            println("i - " + i + "  |   j - " + j + "  |   heightAr[j - i] - " + heightAr[j - i] + "  |   heightAr[j] - " + heightAr[j] + "  |   area - " + area)

            if (area > result) result = area
        }
    }
    return result
}

fun minimumOperations(nums: IntArray, target: IntArray): Long {
    val result: Long
    val diffAr = IntArray(target.size)
    for (i in nums.indices) {
        diffAr[i] = target[i] - nums[i]
    }
    diffAr.sort()
    result = if (diffAr[0] < 0) {
        (diffAr[0].times(-1L) + 1) + diffAr.last()
    } else {
        diffAr.last().toLong()
    }

    return result
}

fun addTwoNumbers(a1: IntArray, a2: IntArray): IntArray {
    var result: IntArray

    val number1 = a1.joinToString("").reversed().toInt()
    val number2 = a2.joinToString("").reversed().toInt()

    val sum = number1 + number2
    result = sum.toString().toCharArray().map { it.toString().toInt() }.toIntArray()
    return result
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    var result = IntArray(2)
    if (nums.size == 2) {
        if (nums[0] + nums[1] == target) {
            result = intArrayOf(0, 1)
        }
    } else {
        for (i in 0 until nums.size) {
            for (j in 0 until nums.size) {
                if (i == j) continue
                if (nums[i] + nums[j] == target) {
                    result = intArrayOf(i, j)
                    break
                }
            }
        }
    }
    return result
}

fun searchInsert(nums: IntArray, target: Int): Int {
    var result = -1
    var greaterIndex = -1
    for (i in 0 until nums.size) {
        if (nums[i] == target) {
            result = i
            break
        } else {
            if (target < nums[i]) {
                result = i
                break
            }
        }
    }
    if (result == -1) {
        result = nums.size
    }
    return result
}

val TAG = "======="
fun plusOne(digits: IntArray): IntArray {
    val stringValue: String = digits.joinToString("")
//    println("stringValue: " + stringValue)
    val longValue = stringValue.toBigInteger()
    val newLongValue = longValue.inc()

    val newCharArray = newLongValue.toString().toCharArray()
    val newIntArray = newCharArray.map { it.toString().toInt() }.toIntArray()
    return newIntArray
}

fun removeElement(nums: IntArray, `val`: Int): Int {
//    var result = nums.filter { it != `val` }
//    println("result: " + result)
//    return result.size


    var count = 0
    var charArray = nums.joinToString("").toCharArray()
    for (i in 0 until charArray.size) {
        if (charArray[i].digitToInt() == `val`) {
//            charArray[i] = '_'
            count++
        }
    }
    return nums.size - count
    charArray = charArray.sortedArray()
    for (i in 0 until charArray.size) {
        println(" sorted charArray " + charArray[i])
    }


    println("==")
    return 2
}

fun main() {
//    println(minimumOperations(intArrayOf(1, 1, 3, 4), intArrayOf(4, 1, 3, 2)))
    //  3,0,0,-2
//    [7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6]
//    println(plusOne(intArrayOf(7, 2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7, 4, 9, 4, 7, 0, 1, 1, 1, 7, 4, 0, 0, 6)))

    println(removeElement(intArrayOf(3, 2, 2, 3), 3))
//    println(removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2))
}

// nums = [1,3,2], target = [2,1,4]


// 3,0,0,-2
//Input: height = [1,8,6,2,5,4,8,3,7]
