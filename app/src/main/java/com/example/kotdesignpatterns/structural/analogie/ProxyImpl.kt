package com.example.kotdesignpatterns.structural.analogie

//Le pattern "Proxy" est inspiré de la manière dont les organismes vivants protègent leur intimité et leur sécurité.
// Par exemple, les chenilles peuvent se protéger en se cachant sous des feuilles ou des brindilles.

// Interface représentant un organisme
interface Organisme {
    fun seDeplacer()
}

// Classe représentant une chenille
class Chenille : Organisme {
    override fun seDeplacer() {
        println("La chenille se déplace lentement")
    }
}

// Classe représentant un proxy de chenille
class ProxyChenille(private val chenille: Chenille) : Organisme {
    override fun seDeplacer() {
        seCacher()
        chenille.seDeplacer()
    }

    private fun seCacher() {
        println("Le proxy de chenille se cache sous une feuille")
    }
}

// Fonction de test
fun main() {
    val chenille = Chenille()
    val proxy = ProxyChenille(chenille)

    proxy.seDeplacer()
}
