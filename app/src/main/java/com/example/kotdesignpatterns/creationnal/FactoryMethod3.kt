package com.example.kotdesignpatterns.creationnal

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test


/*Ce code est un exemple d'implémentation d'un design pattern de conception appelé Factory Method.
La classe Country est une classe scellée (sealed) qui définit des classes d'objets pour les pays, ainsi que des classes de données pour certains pays.
La classe scellée est utile pour restreindre les types possibles de pays que l'on peut créer.
Dans cet exemple, il y a quatre types de pays possibles : Canada, Spain, Greece et USA.
La classe Currency contient une propriété code de type String.
La classe CurrencyFactory est un objet compagnon qui contient une fonction currencyForCountry.
Cette fonction prend un objet Country en entrée et retourne un objet Currency correspondant au pays.
Dans la fonction currencyForCountry, il y a une expression when qui retourne une instance de Currency pour chaque type de pays.
Par exemple, si le pays est de type USA, la fonction retourne une instance de Currency avec le code "USD".*/


/*Dans la nature, un exemple de Factory serait la formation des cellules sanguines dans le corps humain.
Le processus de formation des cellules sanguines implique différentes étapes et la production de différentes cellules sanguines, telles que les globules rouges, les globules blancs et les plaquettes.
Le corps utilise des cellules souches pour produire ces différents types de cellules sanguines en fonction des besoins.
Dans la programmation, le design pattern Factory est couramment utilisé pour créer des objets qui ont des dépendances complexes ou pour instancier des objets en fonction des paramètres fournis.
Le Factory pattern permet également de centraliser la logique de création des objets dans une seule méthode ou classe, ce qui facilite la maintenance et la modification du code.*/



sealed class Country {
    object  Canada: Country()
}

object Spain: Country()
class Greece(val someProperty: String): Country()
data class USA(val someProperty: String): Country()
//class Poland: Country()

class Currency (val code: String)

//FACTORY
object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency =
        when(country) {
            is Spain -> Currency("EUR")
            is Greece -> Currency("EUR")
            is USA -> Currency("USD")
            is Country.Canada -> Currency("CAD")
        }
}

class FactoryMethodTest {
    @Test
    fun currencyTest() {
        val greekCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
        println("Greek currency: $greekCurrency")

        val usaCurrency = CurrencyFactory.currencyForCountry(USA("")).code
        println("USA currency: $usaCurrency")

        Assert.assertEquals(greekCurrency, "EUR")
        assertEquals(usaCurrency,"USD")
    }
}
