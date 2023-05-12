package com.example.kotdesignpatterns.structural

import org.junit.Assert.assertEquals
import org.junit.Test

//La classe Equipment est une classe de base qui représente un équipement avec un prix et un nom.
open class Equipment(
    open val price: Int,
    val name: String
)

//La classe Composite est une classe abstraite qui étend Equipment. Cette classe contient une liste d'Equipment et calcule le prix total des équipements contenus dans la liste
// en utilisant la méthode map qui applique la fonction lambda it.price (qui retourne le prix de chaque équipement)
// à chaque équipement de la liste et en faisant la somme de tous les résultats.

open class Composite(name: String): Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.map { it.price }.sum()

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

//La classe Computer est une classe qui étend Composite et représente un ordinateur.
class Computer: Composite("PC")
//Les classes Processor, HardDrive, ROM, et RAM sont des classes qui étendent Equipment et représentent des composants d'un ordinateur.
class Processor: Equipment(1000, "Processor")
class HardDrive: Equipment(250, "Hard Drive")
//La classe Memory est une classe qui étend Composite et représente la mémoire d'un ordinateur, qui est composée de ROM et de RAM.
class Memory: Composite("Memory")
class ROM: Equipment(100, "Read Only Memory")
class RAM: Equipment(75, "Random Access Memory")


//La classe CompositeTest est une classe de test qui crée un ordinateur avec des composants et vérifie que le prix total est correct.
// On crée un objet Memory avec un ROM et un RAM, puis on crée un objet Computer auquel on ajoute la mémoire, le processeur et le disque dur.
// On vérifie ensuite que le nom de l'ordinateur est "PC" et que le prix total est 1425.
class CompositeTest {
    @Test
    fun testComposite() {
        val memory = Memory()
            .add(ROM())
            .add(RAM())
        val pc = Computer()
            .add(memory)
            .add(Processor())
            .add(HardDrive())
        println("PC price: ${pc.price}")

        assertEquals(pc.name,"PC")
        assertEquals(pc.price,1425)
    }
}