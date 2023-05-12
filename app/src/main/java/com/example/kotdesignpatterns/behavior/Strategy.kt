package com.example.kotdesignpatterns.behavior

import org.junit.Test

//La classe Printer a un constructeur qui prend une stratégie de formatage de chaîne en tant que paramètre.
//La méthode printString de la classe Printer prend une chaîne de caractères en entrée,
// applique la stratégie de formatage de chaîne fournie lors de la construction de l'objet Printer, et imprime la chaîne de caractères formatée sur la console.

class Printer(private val stringFormatterStrategy: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatterStrategy(message))
    }
}

/*Le code définit également deux stratégies de formatage de chaîne: lowercaseFormatter et uppercaseFormatter.
La première transforme la chaîne en minuscules, tandis que la deuxième la transforme en majuscules.*/

val lowercaseFormatter = {it: String -> it.toLowerCase()}
val uppercaseFormatter = {it: String -> it.toUpperCase()}



//La classe StrategyTest contient une méthode de test qui crée un objet Printer avec chaque stratégie de formatage de chaîne, puis appelle la méthode printString avec une chaîne d'entrée.
// Le test vérifie que les sorties sont correctement formatées en minuscules et en majuscules.

class StrategyTest {
    @Test
    fun testStrategy() {
        val inputString = "LOREM ipsum DOLOR sit amet"

        val lowercasePrinter = Printer(lowercaseFormatter)
        lowercasePrinter.printString(inputString)

        val uppercasePrinter = Printer(uppercaseFormatter)
        uppercasePrinter.printString(inputString)
    }
}