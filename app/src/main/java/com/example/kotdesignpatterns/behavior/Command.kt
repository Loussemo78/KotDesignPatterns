package com.example.kotdesignpatterns.behavior

import org.junit.Test

//l'interface Command définit la méthode execute qui sera implémentée par les différentes classes de commandes
interface Command {
    fun execute()
}

//OrderAddCommand qui représente la commande d'ajout d'une commande avec un identifiant donné.
class OrderAddCommand(val id: Long) : Command {
    override fun execute() {
        println("Adding order with id $id")
    }
}

//OrderPayCommand qui représente la commande de paiement d'une commande avec un identifiant donné.
class OrderPayCommand(val id: Long) : Command {
    override fun execute() {
        println("Paying for order with id $id")
    }
}

/*La classe CommandProcessor possède une liste de commandes à traiter, et deux méthodes :
addToQueue qui ajoute une commande à la liste de commandes à traiter.
 Cette méthode renvoie l'objet CommandProcessor lui-même pour permettre l'appel de méthodes chaînées.
processCommands qui exécute toutes les commandes de la liste, puis vide la liste.*/

class CommandProcessor {
    private val queue = arrayListOf<Command>()

    fun addToQueue(command: Command): CommandProcessor = apply { queue.add(command) }

    fun processCommands(): CommandProcessor = apply {
        queue.forEach { it.execute() }
        queue.clear()
    }
}

class CommandTest {
    @Test
    fun testCommand() {
        CommandProcessor()
            .addToQueue(OrderAddCommand(1L))
            .addToQueue(OrderAddCommand(2L))
            .addToQueue(OrderPayCommand(2L))
            .addToQueue(OrderPayCommand(1L))
            .processCommands()
    }
}