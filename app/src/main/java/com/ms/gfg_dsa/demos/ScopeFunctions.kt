package com.ms.gfg_dsa.demos


fun main() {
    val userProfile: UserProfile = UserProfile("Ram", 25, "Ayodhya")
    // Attempting to use `with` for initialization:
    val obj : UserProfile= with(userProfile) {
        name = "Alice"
        age = 30
        city = "New York"
        userProfile

    }

    obj.run {

    }

    println((obj as UserProfile).name)
}


data class UserProfile(
    var name: String = "",
    var age: Int = 0,
    var city: String = ""
)