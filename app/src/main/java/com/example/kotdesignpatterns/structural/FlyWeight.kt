package com.example.kotdesignpatterns.structural

import org.junit.Assert.assertEquals
import org.junit.Test

// Définition de l'interface qui sera implémentée par les objets partagés
interface Flyweight {
    fun operation(state: String)
}

// Implémentation de l'objet partagé
//La classe ConcreteFlyweight implémente cette interface et stocke un état intrinsèque qui est passé dans son constructeur.
// Lorsque la méthode operation est appelée, l'état intrinsèque est affiché ainsi que l'état extrinsèque qui a été passé en paramètre.

class ConcreteFlyweight(private val intrinsicState: String) : Flyweight {
    override fun operation(state: String) {
        println("Intrinsic state: $intrinsicState, Extrinsic state: $state")
    }
}

// Gestionnaire de l'objet partagé
//La classe FlyweightFactory est un gestionnaire d'objets partagés. Elle contient une collection de Flyweight et une méthode getFlyweight qui prend une clé en paramètre.
// Si l'objet correspondant à cette clé existe déjà dans la collection, il est retourné.
// Sinon, un nouvel objet ConcreteFlyweight est créé avec la clé comme état intrinsèque, stocké dans la collection et retourné.

class FlyweightFactory {
    private val flyweights = mutableMapOf<String, Flyweight>()

    fun getFlyweight(key: String): Flyweight {
        return if (flyweights.containsKey(key)) {
            flyweights[key]!!
        } else {
            val flyweight = ConcreteFlyweight(key)
            flyweights[key] = flyweight
            flyweight
        }
    }
}

//La classe FlyweightTest est une classe de test qui crée une instance de FlyweightFactory et utilise deux fois l'objet partagé avec la clé "A" en appelant la méthode operation.
// Elle vérifie ensuite que les deux objets retournés par la méthode getFlyweight sont identiques en utilisant la fonction assertEquals.
//En résumé, ce code montre comment utiliser le design pattern Flyweight pour partager des objets ayant des états similaires afin d'optimiser l'utilisation de la mémoire.

class FlyweightTest {
    @Test
    fun testFlyweight() {
        val factory = FlyweightFactory()

        // Première utilisation de l'objet partagé
        val flyweight1 = factory.getFlyweight("A")
        flyweight1.operation("B")

        // Deuxième utilisation de l'objet partagé
        val flyweight2 = factory.getFlyweight("A")
        flyweight2.operation("C")

        // Vérification que les deux objets sont identiques
        assertEquals(flyweight1, flyweight2)
    }
}
