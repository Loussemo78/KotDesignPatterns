package com.example.kotdesignpatterns.behavior.analogie

// Définition de l'interface Iterator
interface Iterator<T> {
    fun hasNext(): Boolean
    fun next(): T
}

// Définition de la classe de la collection
class Territoire(private val proies: List<String>) {
    // Méthode qui renvoie un itérateur pour parcourir la collection
    fun createIterator(): Iterator<String> {
        return ProieIterator()
    }

    // Implémentation de l'itérateur
    private inner class ProieIterator : Iterator<String> {
        private var index = 0

        override fun hasNext(): Boolean {
            return index < proies.size
        }

        override fun next(): String {
            if (hasNext()) {
                return proies[index++]
            }
            throw NoSuchElementException()
        }
    }
}

// Exemple d'utilisation
fun main() {
    val territoire = Territoire(listOf("souris", "oiseau", "lapin"))

    val it = territoire.createIterator()
    while (it.hasNext()) {
        val proie = it.next()
        println("Le chat repère une proie : $proie")
    }
}
