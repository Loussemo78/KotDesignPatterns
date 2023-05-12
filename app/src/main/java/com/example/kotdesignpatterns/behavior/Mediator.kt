package com.example.kotdesignpatterns.behavior

import org.junit.Test

//La classe "ChatUser" représente un utilisateur d'un système de chat, qui peut envoyer ou recevoir des messages.
// Chaque instance de cette classe est créée avec une référence à une instance de la classe "Mediator",
// qui sera utilisée pour transmettre les messages entre les différents utilisateurs.

class ChatUser(private val mediator: Mediator, private val name: String) {
    fun send(msg: String) {
        println("$name: Sending message: $msg")
        mediator.sendMessage(msg, this)
    }

    fun receive(msg: String) {
        println("$name: Received message: $msg")
    }
}

//La classe "Mediator" maintient une liste d'utilisateurs (instances de "ChatUser") qui sont inscrits au système de chat, et fournit une méthode "sendMessage" qui permet à un utilisateur donné d'envoyer un message à tous les autres utilisateurs.
// Pour cela, la méthode filtre la liste des utilisateurs pour exclure celui qui envoie le message, et appelle la méthode "receive" de chaque utilisateur restant pour leur transmettre le message.
class Mediator {
    private val users = arrayListOf<ChatUser>()

    fun sendMessage(msg: String, user: ChatUser) {
        users
            .filter { it != user }
            .forEach { it.receive(msg) }
    }

    fun addUser(user: ChatUser): Mediator = apply { users.add(user) }
}

class MediatorTest {
    @Test
    fun testMediator() {
        val mediator = Mediator()
        val alice = ChatUser(mediator, "Alice")
        val bob = ChatUser(mediator, "Bob")
        val carol = ChatUser(mediator, "Carol")

        mediator.addUser(alice)
            .addUser(bob)
            .addUser(carol)

        carol.send("Hi everyone!")
    }
}