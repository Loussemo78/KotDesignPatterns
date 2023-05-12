package com.example.kotdesignpatterns.structural.analogie

//Le pattern "Facade" est inspiré de la manière dont les structures naturelles sont organisées pour cacher leur complexité interne.
// Par exemple, les coquillages ont une coquille extérieure qui cache leur structure interne complexe.


// Sous-système complexe représentant la structure interne d'un coquillage
class StructureInterne {
    fun construire() {
        println("Construire la structure interne du coquillage")
    }
}

// Sous-système complexe représentant les motifs décoratifs d'un coquillage
class MotifsDecoratifs {
    fun ajouterMotifs() {
        println("Ajouter les motifs décoratifs sur la coquille")
    }
}

// Facade représentant la coquille d'un coquillage
class Coquille {
    private val structureInterne: StructureInterne = StructureInterne()
    private val motifsDecoratifs: MotifsDecoratifs = MotifsDecoratifs()

    fun assemblerCoquille() {
        structureInterne.construire()
        motifsDecoratifs.ajouterMotifs()
        println("Assembler la coquille du coquillage")
    }
}

// Fonction de test
fun main() {
    val coquille = Coquille()
    coquille.assemblerCoquille()
}

