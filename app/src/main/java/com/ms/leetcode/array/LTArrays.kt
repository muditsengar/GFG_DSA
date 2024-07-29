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


fun main() {
    println(minimumOperations(intArrayOf(1,1,3,4), intArrayOf(4,1,3,2)))
    //  3,0,0,-2
}

// nums = [1,3,2], target = [2,1,4]


// 3,0,0,-2
//Input: height = [1,8,6,2,5,4,8,3,7]
