package com.example.kotdesignpatterns.behavior.analogie

// Interface Élément
interface Element {
    fun accepter(visiteur: Visiteur)
}

// Classe d'élément concret : Animal
class Animal(private val nom: String) : Element {
    override fun accepter(visiteur: Visiteur) {
        visiteur.visiter(this)
    }

    fun recevoirStimuli(stimuli: String) {
        println("[$nom] Réceptivité aux stimuli : $stimuli")
    }
}

// Interface Visiteur
interface Visiteur {
    fun visiter(animal: Animal)
}

// Classe de visiteur concret : Soigneur
class Soigneur : Visiteur {
    override fun visiter(animal: Animal) {
        animal.recevoirStimuli("Haut niveau de réceptivité")
    }
}

// Classe de visiteur concret : Observateur
class Observator : Visiteur {
    override fun visiter(animal: Animal) {
        animal.recevoirStimuli("Faible niveau de réceptivité")
    }
}

// Exemple d'utilisation
fun main() {
    val animal = Animal("Lion")
    val soigneur = Soigneur()
    val observateur = Observator()

    animal.accepter(soigneur)
    animal.accepter(observateur)
}
