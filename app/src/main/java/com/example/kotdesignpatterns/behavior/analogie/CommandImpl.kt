package com.example.kotdesignpatterns.behavior.analogie

// Interface représentant une commande
interface Commande {
    fun executer()
}

// Implémentation d'une commande concrète
class CommandeAboyer(private val chien: Chien) : Commande {
    override fun executer() {
        chien.aboyer()
    }
}

// Classe représentant un chien
class Chien {
    fun aboyer() {
        println("Le chien aboie!")
    }
}

// Classe représentant le maître qui donne les ordres
class Maitre {
    private val commandes: MutableMap<String, Commande> = mutableMapOf()

    fun ajouterCommande(signal: String, commande: Commande) {
        commandes[signal] = commande
    }

    fun donnerOrdre(signal: String) {
        val commande = commandes[signal]
        commande?.executer() ?: println("Aucune commande associée à ce signal")
    }
}

// Exemple d'utilisation
fun main() {
    val chien = Chien()
    val maitre = Maitre()

    // Ajout des commandes possibles
    maitre.ajouterCommande("aboie", CommandeAboyer(chien))

    // Donner des ordres au chien
    maitre.donnerOrdre("aboie") // Le chien aboie!
    maitre.donnerOrdre("assis") // Aucune commande associée à ce signal
}
