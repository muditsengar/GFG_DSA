package com.ms.kotlinQ

import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.atomic

class Singleton private constructor() {
    val a: Int = 0

    val instance3: Singleton? by lazy { Singleton() }

    companion object {
        val atomicInt: AtomicInt = atomic(0)
        val instance1: Singleton by lazy { Singleton() }
        val instance2: Singleton by lazy { Singleton() }

        fun getInstance(): Singleton {
            return if (atomicInt.getAndIncrement() % 2 == 0) {
                instance1
            } else {
                instance2
            }
        }
    }
}