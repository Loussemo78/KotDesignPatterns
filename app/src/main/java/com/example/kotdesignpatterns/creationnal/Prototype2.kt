package com.example.kotdesignpatterns.creationnal

import org.junit.Assert.assertEquals
import org.junit.Test
/*Ce code est un exemple de mise en œuvre du design pattern Prototype en Kotlin.
Le design pattern Prototype permet de cloner des objets plutôt que de créer de nouveaux objets à partir de zéro, ce qui peut être utile dans des cas où la création d'objets est coûteuse ou complexe.
Dans ce code, la classe Shape est une classe abstraite qui représente une forme.
Elle possède deux propriétés id et type, ainsi qu'une méthode abstraite draw()
qui est implémentée par ses sous-classes concrètes Rectangle, Square et Circle. La classe Shape implémente également l'interface Cloneable, ce qui permet de cloner les objets de cette classe.
La classe ShapeCache est une classe singleton qui permet de stocker les formes pré-définies et de les cloner selon le besoin.
Elle utilise une structure de données de type HashMap pour stocker les formes pré-définies en fonction de leur identifiant unique. La méthode loadCache() est utilisée pour pré-charger les formes pré-définies dans la HashMap.
La méthode getShape(shapeId: String): Shape de la classe ShapeCache est utilisée pour cloner une forme en fonction de son identifiant unique.
Elle recherche la forme correspondante dans la HashMap et la clone à l'aide de la méthode clone() de la classe Shape.*/


/*Dans la nature, un exemple de Prototype pourrait être la reproduction asexuée de certains organismes, tels que les bactéries ou les plantes.
Ces organismes se reproduisent en clonant eux-mêmes, créant ainsi des copies identiques d'eux-mêmes avec les mêmes caractéristiques génétiques.*/

abstract class Shape: Cloneable {
    var id: String? = null
    var type: String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null
        try {
            clone = super.clone()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!
    }
}

class Rectangle: Shape() {
    override fun draw() {
        println("Inside Rectangle::draw() method.")
    }

    init {
        type = "Rectangle"
    }
}

class Square: Shape() {
    override fun draw() {
        println("Inside Square::draw() method.")
    }

    init {
        type = "Square"
    }
}

class Circle: Shape() {
    override fun draw() {
        println("Inside Circle::draw() method.")
    }

    init {
        type = "Circle"
    }
}

//PROTOTYPE
object ShapeCache {
    private val shapeMap = hashMapOf<String?, Shape>()

    fun loadCache() {
        val circle = Circle()
        val square = Square()
        val rectangle = Rectangle()

        shapeMap["1"] = circle
        shapeMap["2"] = square
        shapeMap["3"] = rectangle
    }

    fun getShape(shapeId: String): Shape {
        val cachedShape = shapeMap[shapeId]
        return cachedShape?.clone() as Shape
    }
}

class PrototypeTest {
    @Test
    fun testPrototype() {
        ShapeCache.loadCache()
        val clonedShape1 = ShapeCache.getShape("1")
        val clonedShape2 = ShapeCache.getShape("2")
        val clonedShape3 = ShapeCache.getShape("3")

        clonedShape1.draw()
        clonedShape2.draw()
        clonedShape3.draw()

        assertEquals(clonedShape1.type,"Circle")
        assertEquals(clonedShape2.type,"Square")
        assertEquals(clonedShape3.type,"Rectangle")
    }
}