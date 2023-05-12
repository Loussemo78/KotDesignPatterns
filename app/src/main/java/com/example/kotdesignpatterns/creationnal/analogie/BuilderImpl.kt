package com.example.kotdesignpatterns.creationnal.analogie
//Dans la nature, un exemple de Builder pourrait être la construction d'un nid d'oiseau.
// Les oiseaux utilisent différents matériaux et techniques pour construire leur nid en fonction de leur espèce, de leur environnement et de leurs besoins.
// La construction se fait étape par étape, en utilisant différents matériaux et techniques à chaque étape, jusqu'à ce que le nid soit terminé


// Classe représentant le nid d'oiseau
class NidOiseau(private val materiaux: List<String>, private val techniques: List<String>) {
    fun afficherNid() {
        println("Le nid d'oiseau est construit avec les matériaux : $materiaux")
        println("Techniques utilisées : $techniques")
    }
}

// Builder pour la construction du nid d'oiseau
class NidOiseauBuilder {
    private val materiaux: MutableList<String> = mutableListOf()
    private val techniques: MutableList<String> = mutableListOf()

    fun ajouterMateriau(materiau: String) {
        materiaux.add(materiau)
    }

    fun ajouterTechnique(technique: String) {
        techniques.add(technique)
    }

    fun construireNid(): NidOiseau {
        return NidOiseau(materiaux, techniques)
    }
}

// Directeur pour la construction du nid d'oiseau
class Directeur(private val builder: NidOiseauBuilder) {
    fun construireNid() {
        builder.ajouterMateriau("Branches")
        builder.ajouterTechnique("Tressage")
        builder.ajouterMateriau("Feuilles")
        builder.ajouterTechnique("Assemblage")
        builder.ajouterMateriau("Mousse")
        builder.ajouterTechnique("Rembourrage")
    }
}

// Fonction de test
fun main() {
    val builder = NidOiseauBuilder()
    val directeur = Directeur(builder)

    directeur.construireNid()
    val nidOiseau = builder.construireNid()

    nidOiseau.afficherNid()
}
