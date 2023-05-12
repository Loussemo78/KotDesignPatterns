package com.example.kotdesignpatterns.behavior

import org.junit.Assert.assertEquals
import org.junit.Test

//La classe Memento qui contient simplement un champ state qui représente l'état interne d'un objet à un moment donné.
data class Memento(val state: String)


//La classe Originator qui représente un objet dont l'état interne peut être capturé et restauré.
//Elle possède une méthode createMemento() qui crée un objet Memento contenant son état interne actuel, et une méthode restoreMemento(memento: Memento) qui permet de restaurer son état interne à partir d'un objet Memento.

class Originator(var state: String) {
    fun createMemento() = Memento(state)
    fun restoreMemento(memento: Memento) {
        state = memento.state
    }
}

//La classe CareTaker qui agit comme un gestionnaire de memento. Elle stocke une liste d'objets Memento créés par l'objet Originator.
// Elle possède une méthode saveState(state: Memento) qui ajoute un objet Memento à la liste, et une méthode restore(index: Int) qui permet de restaurer un objet Memento à partir de son index dans la liste.
class CareTaker {
    private val mementoList = arrayListOf<Memento>()

    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restore(index: Int): Memento = mementoList[index]
}

/*Le test MementoTest crée un objet Originator initialisé avec l'état "initial state", puis crée un objet CareTaker.
Il capture l'état initial de l'objet Originator en appelant careTaker.saveState(originator.createMemento()), puis modifie l'état de l'objet Originator plusieurs fois en appelant originator.state = ... et careTaker.saveState(originator.createMemento()).
Ensuite, le test restaure l'état de l'objet Originator en appelant originator.restoreMemento(careTaker.restore(...)) plusieurs fois avec différents indices.
Il compare également l'état de l'objet Originator à différentes étapes du test avec l'aide de la méthode assertEquals.
Finalement, le test assure que l'état final de l'objet Originator correspond bien à l'état attendu.*/


class MementoTest {
    @Test
    fun testMemento() {
        val originator = Originator("initial state")
        val careTaker = CareTaker()
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        originator.state = "State 1"
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        originator.state = "State 2"
        careTaker.saveState(originator.createMemento())
        println("Current state is ${originator.state}")

        assertEquals(originator.state,"State 2")

        originator.restoreMemento(careTaker.restore(1))
        println("Current state is ${originator.state}")
        assertEquals(originator.state,"State 1")

        originator.restoreMemento(careTaker.restore(0))
        println("Current state is ${originator.state}")
        assertEquals(originator.state,"initial state")

        originator.restoreMemento(careTaker.restore(2))
        println("Current state is ${originator.state}")
        assertEquals(originator.state,"State 2")
    }
}