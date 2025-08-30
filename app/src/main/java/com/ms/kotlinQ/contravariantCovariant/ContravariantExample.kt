package com.ms.kotlinQ.contravariantCovariant

interface InExample<in T> {
    fun consume(input: T)

    // this will not compile
//    fun returnConsumed(): T
}

// Usage
class StringInExample : InExample<String> {
    override fun consume(input: String) {
        println(input)
    }
}

fun main() {
    val example: InExample<String> = StringInExample()
    example.consume("Just a string") // Just a string

    val outExample: OutExample<String> = StringOutExample()
    println(outExample.returnValue()) // Just a string
}


interface OutExample<out T> {
    fun returnValue(): T
}

// Usage
class StringOutExample : OutExample<String> {
    override fun returnValue() = "Just a string"
}


interface DungeonFactory<out W, in F> {
    fun createWall(): W
    fun createFloor(f: F)
}

class CastleDungeonFactory : DungeonFactory<String, Int> {
    override fun createWall(): String {
        return ""
    }

    override fun createFloor(f: Int) {}
}


/*******************  NEXT EXAMPLE  *****************/

open class Remote {
    override fun toString(): String = "Remote"
}

open class TVRemote : Remote() {
    override fun toString(): String = "TV Remote"

    open fun onRedButtonClicked() {
        println("TV turns ON or OFF")
    }
}

open class ACRemote : Remote() {
    override fun toString(): String = "AC Remote"

    open fun onONOFFButtonClicked() {
        println("AC turns ON or OFF")
    }
}

class BrokenTVRemote : TVRemote() {
    override fun toString(): String = "Broken TV Remote"
    override fun onRedButtonClicked() {
        println("TV doesn't turn ON or OFF")
    }
}

class BrokenACRemote : ACRemote() {
    override fun toString(): String = "Broken AC Remote"

    override fun onONOFFButtonClicked() {
        println("AC doesn't turn ON or OFF")
    }
}

// In real life, if someone sits near a remote, you ask them for it:
class PersonSatDownNearRemote1(private val remote: Remote) {
    fun pleaseGiveMeRemote(): Remote = remote
}


//Let’s modify the PersonSatDownNearRemote class to use generics:
class PersonSatDownNearRemote2<T>(private val remote: T) {
    fun pleaseGiveMeRemote(): T = remote
}

//val sourabh = PersonSatDownNearRemote("Water bottle")
//val nandan = PersonSatDownNearRemote(null)

class PersonSatDownNearRemote<T : Remote>(private val remote: T) {
    fun pleaseGiveMeRemote(): T = remote
}


/*******************  NEXT EXAMPLE  *****************/
open class Fruit {
    fun eatFruit() {}
}

class Apple : Fruit() {

}

class Banana : Fruit() {

}


fun main1(){

    var apple: Fruit
    var banana: Fruit

    apple = Apple()
    banana = Banana()

}

