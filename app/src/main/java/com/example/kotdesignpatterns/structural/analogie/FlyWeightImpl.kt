package com.example.kotdesignpatterns.structural.analogie

// Interface représentant un os
interface Os {
    fun afficherStructure()
}

// Classe concrète représentant un os d'oiseau
class OsOiseau : Os {
    override fun afficherStructure() {
        println("Cet os est creux et léger")
    }
}

// Classe Flyweight Factory pour gérer les os
class FabriqueOs {
    private val os: MutableMap<String, Os> = mutableMapOf()

    fun getOs(type: String): Os {
        return os.getOrPut(type) {
            println("Création d'un nouvel os de type $type")
            OsOiseau()
        }
    }
}

// Fonction de test
fun main() {
    val fabriqueOs = FabriqueOs()

    val os1 = fabriqueOs.getOs("oiseau")
    os1.afficherStructure()

    val os2 = fabriqueOs.getOs("oiseau")
    os2.afficherStructure()
}
