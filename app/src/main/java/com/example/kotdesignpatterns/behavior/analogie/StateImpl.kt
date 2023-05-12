package com.example.kotdesignpatterns.behavior.analogie

// Interface État
interface Etat {
    fun effectuerAction()
}

// Classe Contexte
class Context {
    private var etat: Etat? = null

    // Méthode pour définir l'état actuel
    fun definirEtat(etat: Etat) {
        this.etat = etat
    }

    // Méthode pour exécuter l'action en fonction de l'état actuel
    fun executerAction() {
        etat?.effectuerAction()
    }
}

// Classe État concret : Aggressif
class EtatAggressif : Etat {
    override fun effectuerAction() {
        println("L'animal est agressif. Il attaque!")
    }
}

// Classe État concret : Calme
class EtatCalme : Etat {
    override fun effectuerAction() {
        println("L'animal est calme. Il se repose.")
    }
}

// Exemple d'utilisation
fun main() {
    val contexte = Context()

    val etatAggressif = EtatAggressif()
    val etatCalme = EtatCalme()

    // Définition de l'état initial
    contexte.definirEtat(etatCalme)
    contexte.executerAction()

    // Changement d'état
    contexte.definirEtat(etatAggressif)
    contexte.executerAction()
}
