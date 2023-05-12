package com.example.kotdesignpatterns.structural

import org.junit.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine: CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal coffee machine: Making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal coffee machine: Making large coffee")
    }
}

// Decorator
//La classe EnhancedCoffeeMachine est le décorateur. Elle prend une instance de CoffeeMachine en paramètre de son constructeur et implémente également l'interface CoffeeMachine en délégant l'appel de ses méthodes à l'instance de CoffeeMachine passée en paramètre.
// C'est ce qu'on appelle la délégation par propriété (by).
// La classe EnhancedCoffeeMachine peut également redéfinir le comportement de certaines méthodes de l'interface, comme c'est le cas pour la méthode makeLargeCoffee().
//De plus, la classe EnhancedCoffeeMachine peut également étendre le comportement en ajoutant une nouvelle méthode makeMilkCoffee().
// Cette méthode utilise l'instance de CoffeeMachine pour effectuer une petite tasse de café, puis ajoute du lait.

class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine): CoffeeMachine by coffeeMachine {
    // Overriding behaviour
    override fun makeLargeCoffee() {
        println("Enhanced coffee machine: Making large coffee")
    }

    // Extending behaviour
    fun makeMilkCoffee() {
        println("Enhanced coffee machine: Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced coffee machine: Adding milk")
    }
}
//Dans le test unitaire DecoratorTest, on peut voir que l'on crée une instance de NormalCoffeeMachine, puis on la passe comme argument au constructeur d'une instance de EnhancedCoffeeMachine.
// On peut ensuite appeler les méthodes de CoffeeMachine sur l'objet enhancedMachine, qui appelle ensuite les méthodes correspondantes de l'objet normalMachine, sauf si une méthode a été redéfinie dans la classe EnhancedCoffeeMachine.
//Le décorateur permet ainsi d'ajouter des fonctionnalités à une classe existante de manière dynamique, sans modifier son comportement de base.

class DecoratorTest {
    @Test
    fun testDecorator() {
        val normalMachine = NormalCoffeeMachine()
        val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

        enhancedMachine.makeSmallCoffee()
        println("------------------")
        enhancedMachine.makeLargeCoffee()
        println("------------------")
        enhancedMachine.makeMilkCoffee()
    }
}