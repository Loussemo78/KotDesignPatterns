package com.example.kotdesignpatterns.creationnal

import org.junit.Assert.assertEquals
import org.junit.Test

//SINGLETON
/*Le code présenté définit une classe Kotlin nommée NetworkDriver, qui dispose d'un objet compagnon nommé NetworkDriver.
L'objet compagnon NetworkDriver est utilisé pour créer une instance unique de la classe NetworkDriver, conformément au design pattern Singleton.
La première partie du code, object NetworkDriver, définit l'objet compagnon NetworkDriver.
Il dispose d'un bloc init qui est exécuté lors de la création de l'objet compagnon.
Le bloc init affiche un message dans la console pour indiquer que l'initialisation de l'objet compagnon a commencé.
Le message utilise la référence $this pour faire référence à l'objet compagnon en cours de création.*/


// Le design pattern Singleton est assimilé au pattern de création dans la nature.
// Dans la nature, il y a souvent des entités uniques qui sont créées et qui ont une seule instance.
// Par exemple, le soleil, la lune et la Terre sont des entités uniques qui ont une seule instance dans l'univers.
// De même, dans la programmation, le pattern Singleton permet de créer une classe qui a une seule instance dans l'application.
// Ce modèle est souvent utilisé lorsque vous voulez limiter le nombre d'instances d'une classe à une seule, pour des raisons de performance ou de sécurité

object NetworkDriver {
    init {
        println("Initializing: $this")
    }

    fun log(): NetworkDriver = apply { println("Network driver: $this") }
}

//autre exemple
/*class NetworkDriver private constructor() {

    init {
        println("Initializing: $this")
    }

    companion object {
        private val instance = NetworkDriver()

        fun getInstance(): NetworkDriver {
            return instance
        }
    }

    fun log(): NetworkDriver = apply { println("Network driver: $this") }
}*/

class SingletonTest {
    @Test
    fun testSingleton() {
        println("Start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        assertEquals(networkDriver1, NetworkDriver)
        assertEquals(networkDriver2, NetworkDriver)
    }
}