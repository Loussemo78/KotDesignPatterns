package com.example.kotdesignpatterns.behavior.analogie

// Interface Mediateur
interface Mediateur {
    fun envoyer(message: String, participant: Participant)
}

// Classe de participant abstraite
abstract class Participant(val nom: String, private val mediateur: Mediateur) {
    fun envoyerMessage(message: String) {
        mediateur.envoyer(message, this)
    }

    abstract fun recevoirMessage(message: String)
}

// Classe de participant concret : Lion
class Lion(nom: String, mediateur: Mediateur) : Participant(nom, mediateur) {
    override fun recevoirMessage(message: String) {
        println("[$nom] Message reçu : $message")
        println("[$nom] Rugissement puissant !")
    }
}

// Classe de participant concret : Gazelle
class Gazelle(nom: String, mediateur: Mediateur) : Participant(nom, mediateur) {
    override fun recevoirMessage(message: String) {
        println("[$nom] Message reçu : $message")
        println("[$nom] S'enfuit rapidement !")
    }
}

// Classe de médiateur concret : Savane
class Savane : Mediateur {
    private val participants: MutableList<Participant> = mutableListOf()

    fun ajouterParticipant(participant: Participant) {
        participants.add(participant)
    }

    override fun envoyer(message: String, participant: Participant) {
        participants.forEach { p ->
            if (p != participant) {
                p.recevoirMessage(message)
            }
        }
    }
}

// Exemple d'utilisation
fun main() {
    val savane = Savane()

    val lion = Lion("Simba", savane)
    val gazelle = Gazelle("Giselle", savane)

    savane.ajouterParticipant(lion)
    savane.ajouterParticipant(gazelle)

    lion.envoyerMessage("Attention ! Présence d'une gazelle !")
}
