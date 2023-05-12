package com.example.kotdesignpatterns.behavior.analogie

//Modèle de chaîne de responsabilité : Ce modèle est inspiré de la façon dont les animaux hiérarchisent les rôles et les responsabilités.
// Par exemple, dans une meute de loups, chaque loup a un rôle spécifique et la hiérarchie de la meute permet une meilleure coordination.

// Définition de la classe représentant un loup
class Loup(val nom: String, val role: String) {
    var prochainLoup: Loup? = null

    fun traiterDemande(demande: String) {
        if (peutTraiter(demande)) {
            traiter(demande)
        } else if (prochainLoup != null) {
            prochainLoup!!.traiterDemande(demande)
        } else {
            println("Demande non traitée")
        }
    }

    private fun peutTraiter(demande: String): Boolean {
        // Vérifier si ce loup peut traiter la demande en fonction de son rôle
        // Retourner true si le loup peut traiter la demande, sinon false
        // Exemple: return role == "Alpha" && demande == "Chasser"
        return true
    }

    private fun traiter(demande: String) {
        // Effectuer le traitement de la demande
        println("$nom traite la demande: $demande")
    }
}

// Exemple d'utilisation
fun main() {
    // Création des loups
    val alpha = Loup("Alpha", "Alpha")
    val beta = Loup("Beta", "Beta")
    val gamma = Loup("Gamma", "Gamma")

    // Définition de la hiérarchie de la meute
    alpha.prochainLoup = beta
    beta.prochainLoup = gamma

    // Traitement des demandes
    alpha.traiterDemande("Chasser") // Alpha traite la demande: Chasser
    alpha.traiterDemande("Patrouiller") // Beta traite la demande: Patrouiller
    alpha.traiterDemande("Repos") // Gamma traite la demande: Repos
    alpha.traiterDemande("Chasser") // Demande non traitée
}
