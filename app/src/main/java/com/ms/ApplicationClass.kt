package com.ms

import android.app.Application
import com.ms.hilt.UserRepo
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationClass : Application() {

//    @Inject
//    lateinit var userRepo: UserRepo

    override fun onCreate() {
        super.onCreate()

//        userRepo.saveInDb("ms", "developer")

        val s = "1234"
        s.length
    }
}