package com.example.kotdesignpatterns.creationnal

import org.junit.Assert
import org.junit.Test

/*Dans cet exemple, nous avons une interface DataSource qui représente une source de données,
ainsi que deux classes concrètes DatabaseDataSource et NetworkDataSource qui implémentent cette interface.
Ensuite, nous avons une classe abstraite DataSourceFactory qui définit une méthode abstraite makeDataSource() pour créer une source de données,
et une méthode statique createFactory() qui retourne une instance concrète d'une sous-classe de DataSourceFactory.
Nous avons également deux sous-classes de DataSourceFactory : NetworkFactory et DatabaseFactory, qui implémentent la méthode makeDataSource()
pour créer respectivement une source de données réseau ou une source de données de base de données
*/

/*Dans la nature, un exemple d'Abstract Factory pourrait être les différentes espèces de plantes.
Chaque espèce de plante peut avoir différents sous-types, tels que les arbres, les herbes, les buissons, etc.
Chaque sous-type peut avoir différentes variantes en fonction des conditions de croissance, des régions géographiques, etc.
Cependant, chaque sous-type partage certaines caractéristiques communes, telles que le mode de reproduction, la photosynthèse, etc.*/

interface DataSource

class DatabaseDataSource: DataSource

class NetworkDataSource: DataSource

//FACTORY
abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T: DataSource> createFactory(): DataSourceFactory =
            when(T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class NetworkFactory: DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

class DatabaseFactory: DataSourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDataSource()
}


/*Enfin, nous avons une classe de test AbstractFactoryTest, qui utilise la méthode statique createFactory()
pour créer une instance concrète de DatabaseFactory et appelle la méthode makeDataSource() pour créer une source de données de type DatabaseDataSource.
Le test vérifie ensuite que la source de données créée est bien une instance de la classe DatabaseDataSource.*/

class AbstractFactoryTest {
    @Test
    fun aftest() {
        val datasourceFactory = DataSourceFactory.createFactory<DatabaseDataSource>()
        val dataSource = datasourceFactory.makeDataSource()
        println("Created datasource $dataSource")

        //Assertions.assertThat(dataSource).isInstanceOf(DatabaseDataSource::class.java)
        //assertTrue(dataSource instanceof DatabaseDataSource);
        Assert.assertEquals(dataSource.javaClass, DatabaseDataSource::class.java)


    }
}