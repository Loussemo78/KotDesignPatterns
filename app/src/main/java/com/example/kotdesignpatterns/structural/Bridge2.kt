package com.example.kotdesignpatterns.structural

import org.junit.Assert.assertEquals
import org.junit.Test
//Le pattern "Bridge" est inspiré de la manière dont les ponts sont construits dans la nature.
// Par exemple, les feuilles des plantes sont souvent reliées à la tige par une structure en forme de pont, appelée "pétiole".

//La classe Device est une interface qui représente un appareil électronique, avec une propriété volume et une méthode getName() qui retourne le nom de l'appareil.
interface Device {
    var volume: Int
    fun getName(): String
}

//Les classes Radio et TV implémentent l'interface Device et définissent leur propre comportement pour la méthode getName().
class Radio: Device {
    override var volume = 0
    override fun getName() = "Radio $this"
}

class TV: Device {
    override var volume = 0
    override fun getName() = "TV $this"
}

//L'interface Remote définit deux méthodes pour augmenter et diminuer le volume de l'appareil.
interface Remote {
    fun volumeUp()
    fun volumeDown()
}


//La classe BasicRemote implémente l'interface Remote et prend un objet Device en paramètre du constructeur.
// Cette classe utilise l'objet Device pour augmenter ou diminuer le volume et afficher les informations à la console.
class BasicRemote(val device: Device): Remote {
    override fun volumeUp() {
        device.volume++
        println("${device.getName()} volume up: ${device.volume}")
    }

    override fun volumeDown() {
        device.volume--
        println("${device.getName()} volume down: ${device.volume}")
    }
}

class BridgeTest {
    @Test
    fun testBridge() {
        val tv = TV()
        val radio = Radio()

        val tvRemote = BasicRemote(tv)
        val radioRemote = BasicRemote(radio)

        tvRemote.volumeUp()
        tvRemote.volumeUp()
        tvRemote.volumeDown()

        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeUp()
        radioRemote.volumeDown()

        assertEquals(tv.volume,1)
        assertEquals(radio.volume,2)
    }
}
