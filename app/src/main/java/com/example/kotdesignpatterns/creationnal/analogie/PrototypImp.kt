package com.example.kotdesignpatterns.creationnal.analogie

import org.junit.Assert
import org.junit.Test

//jeux video exemple

abstract class Person: Cloneable {
    var id:String? = null
    var type:String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone:Any? = null
        try {
           clone = super.clone()
        }catch (e:CloneNotSupportedException){
            e.printStackTrace()
        }
        return clone!!
    }
}

class Warrior: Person(){
    override fun draw() {
        println("Inside Warrior::draw() method.")
    }

    init {
        type = "Warrior"
    }

}

class King: Person(){
    override fun draw() {
        println("Inside King::draw() method.")
    }

    init {
        type = "King"
    }
}

class Dragon: Person(){
    override fun draw() {
        println("Inside Dragon::draw() method.")
    }

    init {
        type = "Dragon"
    }
}

object PersonCache {
    private val personMap = hashMapOf<String?, Person>()

    fun loadCache() {
        val warrior = Warrior()
        val king = King()
        val dragon = Dragon()

        personMap["1"] = warrior
        personMap["2"] = king
        personMap["3"] = dragon
    }

    fun getShape(personId: String): Person {
        val cachedPerson = personMap[personId]
        return cachedPerson?.clone() as Person
    }
}

class PrototypImpTest {
    @Test
    fun testPrototype() {
        PersonCache.loadCache()
        val clonedShape1 = PersonCache.getShape("1")
        val clonedShape2 = PersonCache.getShape("2")
        val clonedShape3 = PersonCache.getShape("3")

        clonedShape1.draw()
        clonedShape2.draw()
        clonedShape3.draw()

        Assert.assertEquals(clonedShape1.type, "Warrior")
        Assert.assertEquals(clonedShape2.type, "King")
        Assert.assertEquals(clonedShape3.type, "Dragon")
    }
}