package com.example.kotdesignpatterns.behavior.analogie

// Interface Stratégie
interface Strategy {
    fun executer()
}

// Implémentations concrètes de la Stratégie
class StrategieChasse : Strategy {
    override fun executer() {
        println("L'animal utilise sa stratégie de chasse.")
    }
}

class StrategieRepos : Strategy {
    override fun executer() {
        println("L'animal utilise sa stratégie de repos.")
    }
}

// Classe Contexte
class Animaux(private var strategie: Strategy) {
    fun changerStrategie(nouvelleStrategie: Strategy) {
        strategie = nouvelleStrategie
    }

    fun utiliserStrategie() {
        strategie.executer()
    }
}

// Exemple d'utilisation
fun main() {
    val strategieChasse = StrategieChasse()
    val strategieRepos = StrategieRepos()

    val animal = Animaux(strategieChasse)
    animal.utiliserStrategie()

    animal.changerStrategie(strategieRepos)
    animal.utiliserStrategie()
}
