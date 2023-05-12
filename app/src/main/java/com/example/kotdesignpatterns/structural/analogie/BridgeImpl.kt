package com.example.kotdesignpatterns.structural.analogie

//Le pattern "Bridge" est inspiré de la manière dont les ponts sont construits dans la nature.
// Par exemple, les feuilles des plantes sont souvent reliées à la tige par une structure en forme de pont, appelée "pétiole".

// Interface représentant le comportement du pétiole
interface Petiole {
    fun attacher()
}

// Classe qui représente un type de pétiole
class TypePetiole : Petiole {
    override fun attacher() {
        println("Le pétiole se fixe à la tige de la plante.")
    }
}

// Classe abstraite représentant une plante
abstract class Plante(protected val petiole: Petiole) {
    abstract fun seDevelopper()
}

// Classe concrète représentant une plante spécifique
class PlanteSpecifique(petiole: Petiole) : Plante(petiole) {
    override fun seDevelopper() {
        println("La plante se développe.")
        petiole.attacher()
    }
}

// Fonction de test
fun main() {
    val typePetiole = TypePetiole()
    val plante = PlanteSpecifique(typePetiole)

    plante.seDevelopper()
}
