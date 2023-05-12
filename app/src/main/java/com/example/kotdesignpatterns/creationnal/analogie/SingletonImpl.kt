package com.example.kotdesignpatterns.creationnal.analogie

//Le design pattern Singleton est assimilé au pattern de création dans la nature.
// Dans la nature, il y a souvent des entités uniques qui sont créées et qui ont une seule instance.
// Par exemple, le soleil, la lune et la Terre sont des entités uniques qui ont une seule instance dans l'univers.
// De même, dans la programmation, le pattern Singleton permet de créer une classe qui a une seule instance dans l'application.
// Ce modèle est souvent utilisé lorsque vous voulez limiter le nombre d'instances d'une classe à une seule, pour des raisons de performance ou de sécurité

class Soleil private constructor() {
    init {
        println("Instance du Soleil créée")
    }

    fun briller() {
        println("Le Soleil brille")
    }

    companion object {
        private var instance: Soleil? = null

        fun getInstance(): Soleil {
            if (instance == null) {
                instance = Soleil()
            }
            return instance as Soleil
        }
    }
}

fun main() {
    val soleil1 = Soleil.getInstance()
    soleil1.briller()

    val soleil2 = Soleil.getInstance()
    soleil2.briller()

    println(soleil1 === soleil2) // Vérification des références, devrait être true
}
