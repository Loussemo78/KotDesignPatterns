package com.example.kotdesignpatterns.behavior.analogie

// Classe représentant l'état d'un objet
data class EtatObjet(val information: String)

// Classe représentant l'objet principal
class Objet(var information: String) {
    // Méthode pour créer un memento capturant l'état actuel de l'objet
    fun creerMemento(): Memento {
        return Memento(EtatObjet(information))
    }

    // Méthode pour restaurer l'état de l'objet à partir d'un memento
    fun restaurerMemento(memento: Memento) {
        information = memento.etatObjet.information
    }

    // Classe interne représentant le memento
    data class Memento(val etatObjet: EtatObjet)
}

// Classe représentant le gardien qui gère les mementos
class Gardien {
    private val mementos: MutableList<Objet.Memento> = mutableListOf()

    // Méthode pour ajouter un memento à la liste
    fun ajouterMemento(memento: Objet.Memento) {
        mementos.add(memento)
    }

    // Méthode pour récupérer un memento de la liste
    fun recupererMemento(index: Int): Objet.Memento {
        return mementos[index]
    }
}

// Exemple d'utilisation
fun main() {
    val objet = Objet("Information initiale")
    val gardien = Gardien()

    // Enregistrement de l'état initial de l'objet
    val memento1 = objet.creerMemento()
    gardien.ajouterMemento(memento1)

    // Modification de l'objet
    objet.information = "Nouvelle information"

    // Enregistrement de l'état modifié de l'objet
    val memento2 = objet.creerMemento()
    gardien.ajouterMemento(memento2)

    // Restauration de l'état initial de l'objet
    objet.restaurerMemento(gardien.recupererMemento(0))
    println("État de l'objet après restauration : ${objet.information}")

    // Restauration de l'état modifié de l'objet
    objet.restaurerMemento(gardien.recupererMemento(1))
    println("État de l'objet après restauration : ${objet.information}")
}
