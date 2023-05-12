package com.example.kotdesignpatterns.structural.analogie

// Interface représentant un papillon

//Le pattern "Decorator" est inspiré de la manière dont les animaux utilisent leur apparence pour se protéger et se camoufler.
// Par exemple, certains papillons ont des motifs sur leurs ailes qui les aident à se fondre dans leur environnement.

interface Papillon {
    fun voler()
}

// Classe concrète représentant un papillon ordinaire
class PapillonOrdinaire : Papillon {
    override fun voler() {
        println("Le papillon vole librement")
    }
}

// Classe abstraite représentant un décorateur de papillon
abstract class DecorateurPapillon(private val papillon: Papillon) : Papillon {
    override fun voler() {
        papillon.voler()
    }
}

// Classe concrète représentant un décorateur de motif sur les ailes
class DecorateurMotifAiles(private val papillon: Papillon) : DecorateurPapillon(papillon) {
    override fun voler() {
        super.voler()
        ajouterMotifAiles()
    }

    private fun ajouterMotifAiles() {
        println("Ajouter un motif sur les ailes du papillon pour le camoufler")
    }
}

// Fonction de test
fun main() {
    val papillonOrdinaire: Papillon = PapillonOrdinaire()
    val papillonCamoufle: Papillon = DecorateurMotifAiles(PapillonOrdinaire())

    papillonOrdinaire.voler()
    println("----")
    papillonCamoufle.voler()
}
