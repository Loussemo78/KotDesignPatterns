package com.example.kotdesignpatterns.behavior.analogie

// Interface Observateur

//Modèle d'observateur : Ce modèle est inspiré de la façon dont les animaux communiquent entre eux pour coordonner leurs comportements.
// Par exemple, un groupe d'oiseaux en vol peut communiquer par des signaux visuels ou sonores pour maintenir leur formation.
interface Observateur {
    fun mettreAJour(message: String)
}

// Classe SujetObservable
class SujetObservable {
    private val observateurs: MutableList<Observateur> = mutableListOf()

    // Méthode pour ajouter un observateur à la liste
    fun ajouterObservateur(observateur: Observateur) {
        observateurs.add(observateur)
    }

    // Méthode pour supprimer un observateur de la liste
    fun supprimerObservateur(observateur: Observateur) {
        observateurs.remove(observateur)
    }

    // Méthode pour notifier tous les observateurs d'un événement
    fun notifierObservateurs(message: String) {
        for (observateur in observateurs) {
            observateur.mettreAJour(message)
        }
    }
}

// Classe ObservateurConcret : Oiseau
class Oiseau(private val nom: String) : Observateur {
    override fun mettreAJour(message: String) {
        println("[$nom] Message reçu : $message")
        println("[$nom] Maintien de la formation en vol")
    }
}

// Exemple d'utilisation
fun main() {
    val sujet = SujetObservable()

    val oiseau1 = Oiseau("Oiseau1")
    val oiseau2 = Oiseau("Oiseau2")
    val oiseau3 = Oiseau("Oiseau3")

    sujet.ajouterObservateur(oiseau1)
    sujet.ajouterObservateur(oiseau2)
    sujet.ajouterObservateur(oiseau3)

    sujet.notifierObservateurs("Changement de direction")

    sujet.supprimerObservateur(oiseau2)

    sujet.notifierObservateurs("Accélération")
}
