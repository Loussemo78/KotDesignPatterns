package com.example.kotdesignpatterns.structural

import org.junit.Test

interface Image {
    fun display()
}

//La classe RealImage est la classe concrète qui charge l'image à partir du disque et l'affiche.
class RealImage(private val filename: String): Image {
    override fun display() {
        println("RealImage: Displaying $filename")
    }

    private fun loadFromDisk(filename: String) {
        println("RealImage: Loading $filename")
    }

    init {
        loadFromDisk(filename)
    }
}

//La classe ProxyImage est une classe proxy pour RealImage, elle permet de charger l'image depuis le disque uniquement lorsque cela est nécessaire.
class ProxyImage(private val filename: String): Image {
    private var realImage: RealImage? = null

    override fun display() {
        println("ProxyImage: Displaying $filename")
        if (realImage == null) {
            realImage = RealImage(filename)
        }
        realImage!!.display()
    }
}

class ProxyTest {
    @Test
    fun testProxy() {
        val image = ProxyImage("test.jpg")

        // load image from disk
        image.display()
        println("-------------------")

        //load image from "cache"
        image.display()
    }
}