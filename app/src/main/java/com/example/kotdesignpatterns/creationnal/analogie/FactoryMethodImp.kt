package com.example.kotdesignpatterns.creationnal.analogie

import org.junit.Assert
import org.junit.Test

sealed class Land {
    object  Centrafrique: Land()
}

object Congo: Land()
class Angola(val someProperty: String): Land()
data class Senegal(val someProperty: String): Land()

class Language (val code:String)

//FACTORY

object LanguageFactory {
    fun languageForLand(land: Land): Language =
        when(land) {
            is Congo -> Language("KIKONGO")
            is Angola -> Language("KIMBUNDU")
            is Senegal -> Language("WOLOF")
            is Land.Centrafrique -> Language("SANGO")
        }
}

class FactoryMethodImpTest {
    @Test
    fun currencyTest() {
        val congoLanguage = LanguageFactory.languageForLand(Congo).code
        println("Congo Language: $congoLanguage")

        val angolaLanguage = LanguageFactory.languageForLand(Angola("")).code
        println("Angola Language: $angolaLanguage")


        Assert.assertEquals(congoLanguage, "KIKONGO")
        Assert.assertEquals(angolaLanguage, "KIMBUNDU")
    }
}