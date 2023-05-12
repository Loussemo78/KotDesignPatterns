package com.example.kotdesignpatterns.behavior.analogie
//Modèle de Template Method : Ce modèle permet de définir le squelette d'un algorithme dans une classe de base, tout en laissant certaines étapes spécifiques à des sous-classes.
// Il est inspiré de la façon dont les comportements instinctifs des animaux peuvent être adaptés en fonction des situations spécifiques.
// Par exemple, les oiseaux construisent des nids en utilisant un modèle de base, mais adaptent leur construction en fonction des matériaux disponibles.

// Classe de base abstraite : Animal
abstract class Animale {
    fun vivre() {
        seNourrir()
        seReproduire()
        adapterComportement()
        dormir()
    }

    abstract fun seNourrir()
    abstract fun seReproduire()
    abstract fun adapterComportement()
    abstract fun dormir()
}

// Classe concrète : Oiseau
class Bird : Animale() {
    override fun seNourrir() {
        println("L'oiseau cherche de la nourriture.")
    }

    override fun seReproduire() {
        println("L'oiseau cherche un partenaire pour se reproduire.")
    }

    override fun adapterComportement() {
        println("L'oiseau construit un nid adapté aux matériaux disponibles.")
    }

    override fun dormir() {
        println("L'oiseau se repose dans son nid.")
    }
}

// Exemple d'utilisation
fun main() {
    val oiseau = Bird()
    oiseau.vivre()
}
