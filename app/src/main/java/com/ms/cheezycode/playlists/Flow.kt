package com.ms.cheezycode.playlists

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val TAG = "Flow======"

fun main() {

    val list: Flow<Int> = listOf(1, 2, 3).asFlow()

    CoroutineScope(Dispatchers.IO).launch {
//        val first = flowProducer.first()
        flowProducer.buffer(3)
            .collect { it: Int ->
            println("collect: " + it)
        }
    }

    CoroutineScope(Dispatchers.IO).launch {
        delay(40)
        flowProducer.map {
            it * 2
        }.filter {
            it < 8
        }.collect { it: Int ->
            Log.e(TAG, "collect====: " + it)
        }
    }
}

val flowProducer = flow<Int> {
    val list = listOf(1, 2, 3, 4, 5)
    list.forEach {
        Log.e(TAG, "flowProducer: $it")
        println("flowProducer: $it")
        delay(500)
        emit(it)
    }
}