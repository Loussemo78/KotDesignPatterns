package com.example.kotdesignpatterns.behavior.analogie

// Interface représentant une expression interprétable
interface Expression {
    fun interpreter(contexte: Contexte): Boolean
}

// Implémentation concrète d'une expression : Expression d'observation
class ExpressionObservation(private val animal: String) : Expression {
    override fun interpreter(contexte: Contexte): Boolean {
        return contexte.observationAnimale.contains(animal)
    }
}

// Implémentation concrète d'une expression : Expression de comportement
class ExpressionComportement(private val comportement: String) : Expression {
    override fun interpreter(contexte: Contexte): Boolean {
        return contexte.comportementAnimal.contains(comportement)
    }
}

// Contexte contenant les informations nécessaires à l'interprétation
class Contexte(val observationAnimale: List<String>, val comportementAnimal: List<String>)

// Exemple d'utilisation
fun main() {
    // Contexte avec les observations et les comportements animaux
    val contexte = Contexte(
        listOf("oiseau", "arbre", "nuage"),
        listOf("voler", "chanter")
    )

    // Expressions
    val expressionObservation = ExpressionObservation("arbre")
    val expressionComportement = ExpressionComportement("voler")

    // Interprétation des expressions
    val resultatObservation = expressionObservation.interpreter(contexte)
    val resultatComportement = expressionComportement.interpreter(contexte)

    // Affichage des résultats
    println("Résultat de l'expression d'observation: $resultatObservation") // true
    println("Résultat de l'expression de comportement: $resultatComportement") // true
}
