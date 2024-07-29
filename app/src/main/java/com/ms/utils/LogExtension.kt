package com.ms.utils

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LogNotTimber")
fun Any.loge(tag: String = "", value: String?) {
    
    val customTag = if (tag.isNotEmpty()) tag else this.javaClass.simpleName
    val messageToDisplay = value ?: "empty message"
    Log.e(customTag, if (tag.isNotEmpty()) "${this.javaClass.simpleName} >> $messageToDisplay" else messageToDisplay)
}
