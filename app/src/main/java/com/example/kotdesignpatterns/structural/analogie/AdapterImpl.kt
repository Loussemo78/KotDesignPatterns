package com.example.kotdesignpatterns.structural.analogie

//Le pattern "Adapter" est inspiré de la manière dont les organismes vivants ont développé des structures qui leur permettent de s'adapter à leur environnement.
// Par exemple, les caméléons peuvent changer la couleur de leur peau pour se camoufler dans leur environnement

// Interface représentant le comportement du caméléon
interface Camouflage {
    fun seCamoufler()
}

// Classe qui représente le caméléon et implémente l'interface Camouflage
class Cameleon : Camouflage {
    override fun seCamoufler() {
        println("Le caméléon change de couleur pour se camoufler.")
    }
}

// Classe Adapter qui adapte le comportement du caméléon en utilisant une méthode de changement de couleur
class CameleonAdapter(private val camelon: Cameleon) : Camouflage {
    override fun seCamoufler() {
        camelon.seCamoufler()
    }
}

// Fonction de test
fun main() {
    val camelon = Cameleon()
    val adapter = CameleonAdapter(camelon)

    adapter.seCamoufler()
}
