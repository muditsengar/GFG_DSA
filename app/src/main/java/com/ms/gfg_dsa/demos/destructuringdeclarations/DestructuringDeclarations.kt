package com.ms.gfg_dsa.demos.destructuringdeclarations


class DestructuringDeclarations {

    val person = Person("ms", "31")

    fun main() {
        val (name: String, age: String) = person
    }

    fun breakObject(person: Person) {
        val (name, age) = person
    }
}


data class Person(
    val name: String,
    val age: String
)

