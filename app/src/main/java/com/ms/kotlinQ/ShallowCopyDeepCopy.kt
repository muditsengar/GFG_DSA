package com.ms.kotlinQ

data class Address(var city: String)

data class User(val name: String, val surname: String, val address: Address)

val address = Address("New York")
val originalUser = User("John", "Smith", address)
val copiedUser = originalUser.copy(name = "Ilyas")

fun main() {
    println(originalUser) // User(name=John, surname=Smith, address=Address(city=New York))
    println(copiedUser) // User(name=Ilyas, surname=Smith, address=Address(city=New York))

// Change the city in original user's address
    originalUser.address.city = "San Francisco"

    println(originalUser) // User(name=John, surname=Smith, address=Address(city=San Francisco))
    println(copiedUser) // User(name=Ilyas, surname=Smith, address=Address(city=San Francisco))
}