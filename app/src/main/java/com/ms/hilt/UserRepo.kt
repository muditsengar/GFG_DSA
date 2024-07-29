package com.ms.hilt

import android.util.Log

class UserRepo {
    fun saveInDb(name: String, title: String) {
        Log.e("======", "saveInDb: User saved")
    }
}