import org.junit.Assert.assertEquals
import org.junit.Test

/*La classe Order est une classe abstraite qui définit le squelette de la méthode process() qui représente le processus général de traitement de la commande.
 Cette méthode appelle trois autres méthodes abstraites doSelect(), doPayment(), et doDelivery().
Ces méthodes doivent être implémentées par les sous-classes pour définir les étapes spécifiques de traitement de commande en fonction du type d'ordre.*/

abstract class Order {
    fun process() {
        doSelect()
        doPayment()
        doDelivery()
    }

    abstract fun doSelect()
    abstract fun doPayment()
    abstract fun doDelivery()
}

//La classe OnlineOrder est une sous-classe de Order qui implémente les méthodes doSelect(), doPayment(), et doDelivery() pour les commandes passées en ligne.
// Elle définit les étapes spécifiques pour sélectionner un article en ligne, effectuer le paiement via une passerelle de paiement en ligne et envoyer l'article au client.

class OnlineOrder : Order() {
    override fun doSelect() {
        println("Selecting item from online store")
    }

    override fun doPayment() {
        println("Making payment for the item using online payment gateway")
    }

    override fun doDelivery() {
        println("Shipping the item to customer's address")
    }
}

//La classe InStoreOrder est également une sous-classe de Order qui implémente les mêmes méthodes abstraites pour les commandes passées en magasin.
// Elle définit les étapes spécifiques pour sélectionner un article dans un magasin, effectuer le paiement à la caisse et remettre l'article au client.

class InStoreOrder : Order() {
    override fun doSelect() {
        println("Selecting item from in-store shelf")
    }

    override fun doPayment() {
        println("Making payment for the item at the cash counter")
    }

    override fun doDelivery() {
        println("Handing over the item to the customer")
    }
}

fun main() {
    val onlineOrder = OnlineOrder()
    onlineOrder.process()

    println()

    val inStoreOrder = InStoreOrder()
    inStoreOrder.process()
}
