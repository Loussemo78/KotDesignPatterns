package com.example.kotdesignpatterns.structural.analogie
//Le pattern "Composite" est inspiré de la manière dont les cellules du corps humain s'organisent pour former des tissus et des organes.
// Chaque cellule individuelle a une fonction spécifique, mais lorsqu'elles sont combinées, elles forment des structures plus complexes et plus efficaces.


// Interface représentant une composante (cellule, tissu ou organe)
interface Composante {
    fun afficherStructure()
}

// Classe représentant une cellule
class Cellule(private val nom: String) : Composante {
    override fun afficherStructure() {
        println("Cellule : $nom")
    }
}

// Classe représentant un tissu composé de plusieurs cellules
class Tissu(private val nom: String) : Composante {
    private val cellules: MutableList<Composante> = mutableListOf()

    fun ajouterCellule(cellule: Composante) {
        cellules.add(cellule)
    }

    override fun afficherStructure() {
        println("Tissu : $nom")
        for (cellule in cellules) {
            cellule.afficherStructure()
        }
    }
}

// Classe représentant un organe composé de plusieurs tissus et éventuellement d'autres organes
class Organe(private val nom: String) : Composante {
    private val composantes: MutableList<Composante> = mutableListOf()

    fun ajouterComposante(composante: Composante) {
        composantes.add(composante)
    }

    override fun afficherStructure() {
        println("Organe : $nom")
        for (composante in composantes) {
            composante.afficherStructure()
        }
    }
}

// Fonction de test
fun main() {
    val cellule1 = Cellule("Cellule 1")
    val cellule2 = Cellule("Cellule 2")
    val cellule3 = Cellule("Cellule 3")

    val tissuA = Tissu("Tissu A")
    tissuA.ajouterCellule(cellule1)
    tissuA.ajouterCellule(cellule2)

    val tissuB = Tissu("Tissu B")
    tissuB.ajouterCellule(cellule3)

    val organe = Organe("Organe")
    organe.ajouterComposante(tissuA)
    organe.ajouterComposante(tissuB)

    organe.afficherStructure()
}
