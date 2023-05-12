package com.example.kotdesignpatterns.structural

import org.junit.Assert.assertEquals
import org.junit.Test


//Tout d'abord, on définit une classe DisplayDataType qui représente une paire de données : un index (de type Float) et une chaîne de caractères (de type String).

//Le pattern "Adapter" est inspiré de la manière dont les organismes vivants ont développé des structures qui leur permettent de s'adapter à leur environnement.
// Par exemple, les caméléons peuvent changer la couleur de leur peau pour se camoufler dans leur environnement.

data class DisplayDataType(val index: Float, val data: String)

//Ensuite, on définit une classe DataDisplay qui possède une méthode displayData qui affiche le contenu d'un objet DisplayDataType.
class DataDisplay {
    fun displayData(data: DisplayDataType) {
        println("Data is displayed: ${data.index} - ${data.data}")
    }
}

// ------------------------
// Our code
//On définit également une classe DatabaseData qui représente des données provenant d'une base de données.
// Ces données ont deux champs : une position (de type Int) et une quantité (de type Int).
data class DatabaseData(val position: Int, val amount: Int)


//La classe DatabaseDataGenerator possède une méthode generateData qui génère une liste de données de la classe DatabaseData.
class DatabaseDataGenerator {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        list.add(DatabaseData(3, 7))
        list.add(DatabaseData(4, 23))
        return list
    }
}

//L'interface DatabaseDataConverter définit une méthode convertData qui prend une liste de données de la classe DatabaseData
// et qui retourne une liste de données de la classe DisplayDataType

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
}

//La classe DataDisplayAdapter implémente l'interface DatabaseDataConverter et utilise la classe DataDisplay pour afficher les données converties.
// Elle convertit les données en bouclant sur chaque élément de la liste de données de la classe DatabaseData et en créant un objet DisplayDataType pour chaque élément.
// Elle affiche ensuite ces données en utilisant la méthode displayData de la classe DataDisplay et ajoute chaque objet DisplayDataType à une nouvelle liste qu'elle retourne.

class DataDisplayAdapter(val display: DataDisplay): DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        for (datum in data) {
            val ddt = DisplayDataType(datum.position.toFloat(), datum.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}
//Enfin, la classe AdapterTest définit un test unitaire pour tester la conversion de données à l'aide de l'adaptateur DataDisplayAdapter.
// Elle génère des données de la classe DatabaseData à l'aide de la classe DatabaseDataGenerator, crée un objet DataDisplayAdapter et utilise sa méthode convertData pour convertir les données en objets DisplayDataType.
// Elle vérifie ensuite que le nombre d'éléments de la liste est correct, que l'index et la donnée de l'élément à l'index 1 sont corrects.

class AdapterTest {
    @Test
    fun adapterTest() {
        val generator = DatabaseDataGenerator()
        val generatedData = generator.generateData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertData = adapter.convertData(generatedData)

        assertEquals(convertData.size,3)
        assertEquals(convertData[1].index,3f)
        assertEquals(convertData[1].data,"7")
    }
}