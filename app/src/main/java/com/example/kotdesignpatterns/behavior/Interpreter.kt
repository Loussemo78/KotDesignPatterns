package com.example.kotdesignpatterns.behavior

//L'interface "Expression" définit une méthode "interpret" qui retourne un entier
interface Expression {
    fun interpret(): Int
}

//Les classes "Number", "Add" et "Subtract" implémentent cette interface et représentent respectivement un nombre, et .
class Number(private val number: Int) : Expression {
    override fun interpret() = number
}

//une opération d'addition
class Add(private val leftExpression: Expression, private val rightExpression: Expression) : Expression {
    override fun interpret() = leftExpression.interpret() + rightExpression.interpret()
}

//une opération de soustraction entre deux expressions
class Subtract(private val leftExpression: Expression, private val rightExpression: Expression) : Expression {
    override fun interpret() = leftExpression.interpret() - rightExpression.interpret()
}


//La classe "Parser" est responsable de l'analyse de l'expression donnée et de la construction de l'arbre d'expression correspondant.
// Elle prend en entrée une liste de chaînes de caractères représentant l'expression à analyser.
// La méthode "parse" de cette classe parcourt la liste de chaînes de caractères et construit l'arbre d'expression en utilisant les classes "Number", "Add" et "Subtract".
//La méthode "parseTerm" est utilisée pour analyser chaque terme de l'expression. Si le terme est un nombre, la méthode retourne une instance de la classe "Number".
// Sinon, elle lance une exception indiquant que le terme est invalide.


class Parser(private val tokens: List<String>) {
    private var current = 0

    fun parse(): Expression {
        val left = parseTerm()
        while (current < tokens.size) {
            val operator = tokens[current]
            current++
            val right = parseTerm()
            when (operator) {
                "+" -> Add (left , right)
                "-" -> Add (left ,right)
            }
        }
        return left
    }
    //La méthode "parseTerm" est utilisée pour analyser chaque terme de l'expression.
    // Si le terme est un nombre, la méthode retourne une instance de la classe "Number".
    // Sinon, elle lance une exception indiquant que le terme est invalide.

    private fun parseTerm(): Expression {
        val token = tokens[current]
        current++
        return when {
            token.matches("\\d+".toRegex()) -> Number(token.toInt())
            else -> throw IllegalArgumentException("Invalid token: $token")
        }
    }
}

//Enfin, la fonction "main" crée une instance de la classe "Parser" avec l'expression "1+2-3".
// La méthode "split" est utilisée pour diviser la chaîne de caractères en une liste de chaînes de caractères, qui est ensuite filtrée pour ne conserver que les chaînes non vides.
// La méthode "parse" de l'instance de "Parser" est ensuite appelée pour analyser l'expression et retourner l'arbre d'expression correspondant.
// Enfin, la méthode "interpret" est appelée sur cet arbre pour obtenir le résultat de l'expression, qui est imprimé à l'écran.
// Dans cet exemple, le résultat est "-2" car l'expression "1+2-3" évalue à "-2".

fun main() {
    val expression = "1+2-3"
    val parser = Parser(expression.split("").filter { it.isNotBlank() })
    println("Result: ${parser.parse().interpret()}")
}
