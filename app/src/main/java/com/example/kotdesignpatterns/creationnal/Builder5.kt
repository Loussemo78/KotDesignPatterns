package com.example.kotdesignpatterns.creationnal

import org.junit.Assert.assertEquals
import org.junit.Test

//Dans la nature, un exemple de Builder pourrait être la construction d'un nid d'oiseau.
// Les oiseaux utilisent différents matériaux et techniques pour construire leur nid en fonction de leur espèce, de leur environnement et de leurs besoins.
// La construction se fait étape par étape, en utilisant différents matériaux et techniques à chaque étape, jusqu'à ce que le nid soit terminé.

class Component private constructor(builder: Builder) {
    var param1: String? = null
    var param2: Int? = null
    var param3: Boolean? = null

    class Builder {
        private var param1: String? = null
        private var param2: Int? = null
        private var param3: Boolean? = null

        fun setParam1(param1: String) = apply { this.param1 = param1 }
        fun setParam2(param2: Int) = apply { this.param2 = param2 }
        fun setParam3(param3: Boolean) = apply { this.param3 = param3 }
        fun build() = Component(this)

        fun getParam1() = param1
        fun getParam2() = param2
        fun getParam3() = param3
    }

    init {
        param1 = builder.getParam1()
        param2 = builder.getParam2()
        param3 = builder.getParam3()
    }
}

class ComponentTest {
    @Test
    fun builderTest() {
        val component = Component.Builder()
            .setParam1("Some value")
            .setParam3(true)
            .build()
        println(component)
        println(component.param1)
        println(component.param3)

        assertEquals(component.param1,"Some value")
        assertEquals(component.param3,true)
        assertEquals(component.param2,null)
    }
}
